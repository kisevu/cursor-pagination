package com.ameda.kev.paginationcursorkeyset.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * Author: kev.Ameda
 */
@Slf4j
@Component
@Order(1)
public class AuthenticationFilter implements Filter {
    private final RequestIDGenerator requestIDGenerator;

    public AuthenticationFilter(RequestIDGenerator requestIDGenerator) {
        this.requestIDGenerator = requestIDGenerator;
    }

    /**
     * N/B: <br/>
     * - Request can be modified while traversing downstream but the reverse is not true for the response.<br/>
     *   the response is read-only. <br/>
     * - so we can do something like response.setHeader("REQUEST-ID") before calling doFilter() <br/>
    * */
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        res.setHeader("X-REQUEST-ID", requestIDGenerator.generateID());

        long startTime = System.currentTimeMillis();

        String token = req.getHeader("authorization");
        if (token == null || !token.equals("secret")){
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.setContentType("application/json");
            String msg  = """
                   {
                        "Error-Id": "0001",
                        "Error-Code": "REQUEST_UNAUTHORIZED",
                        "Error-Message":"Your request was not authenticated."
                    }
                    """;
            res.getWriter().write(msg);
            return;
        }

        chain.doFilter(request, response);
        log.info(" Duration the request took: {}",System.currentTimeMillis() - startTime);
    }
}
