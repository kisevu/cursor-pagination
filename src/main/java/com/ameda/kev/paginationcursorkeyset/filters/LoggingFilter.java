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
@Component
@Slf4j
@Order(2)
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {
       HttpServletRequest request = (HttpServletRequest) servletRequest;
       HttpServletResponse response = (HttpServletResponse) servletResponse;

       log.info(" Http Method : {}, Http  URI : {}", request.getMethod(),
               request.getRequestURI());
       chain.doFilter(servletRequest,servletResponse);
       log.info(" Server returned a status of : {}, " +
                       "server returned a content type of : {}",
               response.getStatus(),
               response.getContentType()
               );
    }
}
