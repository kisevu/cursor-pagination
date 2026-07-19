package com.ameda.kev.paginationcursorkeyset.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Author: kev.Ameda
 */
@Component
@Slf4j
@Order(3)
public class ResponseBodyFilter implements Filter {

    /**
    * We could modify our response using the wrapper as indicated below.<br/>
     * This is normally not a good approach. We shouldn't modify responses that are being created. <br/>
     * Especially tweaking the body, it is not recommended, but It can be done.
    * */
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest)  request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        ContentCachingResponseWrapper wrappedResponse =
                new ContentCachingResponseWrapper(httpServletResponse);

        //Reading of the request body also throws an IllegalStateException,
        // since the Dispatcher servlet is required to read the data and convert it,
        //reading beforehand, errors out.
        // Since it is a stream and data keeps flowing, hence there is no storage for such data.

//        BufferedReader reader = httpServletRequest.getReader();
//
//        StringBuilder sbuilder = new StringBuilder();
//
//        String line = reader.readLine();
//
//         while(line!=null){
//             sbuilder.append(line);
//             line = reader.readLine();
//         }
//
//         log.info("  Body :{} ",sbuilder);

        filterChain.doFilter(request,wrappedResponse);

        byte[] originalBodyBytes = wrappedResponse.getContentAsByteArray();

        String originalBody = new String(originalBodyBytes);
        Long returned =  System.currentTimeMillis();

        // clears buffers and avoids duplications
        wrappedResponse.resetBuffer();

        String modifiedBody =
                """
                {
                   "originalResponseBody": %s,
                   "returned At": %d
                }
                """.formatted(originalBody,returned);

        wrappedResponse.getWriter().write(modifiedBody);
        wrappedResponse.copyBodyToResponse();

    }
}
