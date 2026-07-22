package com.ameda.kev.paginationcursorkeyset.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author: kev.Ameda
 */
@Component
@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {
    /**
     * The architecture flow of request traversal looks something like this. <br/>
     * Client -> Tomcat Server -> Dispatcher servlet -> Handler mapping <br/>
     *  Handler Mapping -> Dispatcher servlet <br/>
     *  Dispatcher servlet -> Interceptor preHandle(). <br/>
     *
     * Request comes from the client and it touches the tomcat server <br/>
     * Tomcat server reaches out to the Dispatcher servlet <br/>
     * The Dispatcher  servlet calls the Handler Mapper (responsible for locating appropriate handler). <br/>
     *  After finding the handler, the Handler mapper reaches back to the Dispatcher servlet. <br/>
     *  Then the Dispatcher servlet reaches out to the Handler Adapter to execute the handler <br/>
    * */

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //All the information present in the controller called is available in the handler object
        log.info("PreHandle Interceptor called.");
        HandlerMethod method = (HandlerMethod) handler;
        log.info(" Controller name :{}",method.getBeanType().getName());
        log.info(" Method name :{}",method.getMethod().getName());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        log.info("PostHandle Interceptor called.");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                @Nullable Exception ex) throws Exception {
        log.info("AfterCompletion Interceptor called.");
        log.info(" Response status :{}", response.getStatus());
    }
}
