package com.ameda.kev.paginationcursorkeyset.filters;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Author: kev.Ameda 
 */
@Slf4j
public class DummyFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        log.info("DummyFilter has executed.");
    }
}
