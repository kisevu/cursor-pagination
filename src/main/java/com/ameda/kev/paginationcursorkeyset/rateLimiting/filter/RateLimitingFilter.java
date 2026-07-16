package com.ameda.kev.paginationcursorkeyset.rateLimiting.filter;

import com.ameda.kev.paginationcursorkeyset.rateLimiting.service.RateLimitingService;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Author: kev.Ameda
 */
@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private final RateLimitingService rateLimitingService;

    public RateLimitingFilter(RateLimitingService rateLimitingService) {
        this.rateLimitingService = rateLimitingService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String clientIP = this.getClientIP(request);
        Bucket bucket = rateLimitingService.resolveBucket(clientIP);

        // Consume 1 token from the bucket
        // Try to consume method tells how many tokens we have  remaining and how long
        // a client needs to wait if the bucket is empty.
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if(probe.isConsumed()){
            response.addHeader("X-Rate-Limit-Remaining", String.valueOf(probe.getRemainingTokens()));
            filterChain.doFilter(request,response);
        }else{
            //Here the client has exhausted the request quota
            //calculate how long the client has to wait before retrying
            var waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000_000;
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.addHeader("X-Rate-Limit_Retry_After-Seconds",String.valueOf(waitForRefill));
            response.setContentType("application/json");

            String jsonResponse = """
                    {
                    "status": %s,
                    "error":  "Too Many Requests",
                    "message":"You  have exhausted your API Quota",
                    "retryAfterSeconds": %d
                    }
                    """.formatted(HttpStatus.TOO_MANY_REQUESTS.value(),waitForRefill);
           response.getWriter().write(jsonResponse);
        }

    }

    private String getClientIP(HttpServletRequest request){
        // check for X-Forwarded-For  headed which is automatically added by proxies hence a
        // better approach.
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null || xfHeader.isEmpty()){
            return request.getRemoteAddr();
        }
        // for multiple proxies, hence multiple IPs.
        // hence the first IP is usually the original client
       return xfHeader.split(",")[0].trim();

    }
}
