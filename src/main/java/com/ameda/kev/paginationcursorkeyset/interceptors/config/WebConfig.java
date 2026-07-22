package com.ameda.kev.paginationcursorkeyset.interceptors.config;

import com.ameda.kev.paginationcursorkeyset.interceptors.EnrichInterceptor;
import com.ameda.kev.paginationcursorkeyset.interceptors.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Author: kev.Ameda
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoggingInterceptor   loggingInterceptor;

    private final EnrichInterceptor enrichInterceptor;

    public WebConfig(LoggingInterceptor loggingInterceptor,
                     EnrichInterceptor enrichInterceptor) {
        this.loggingInterceptor = loggingInterceptor;
        this.enrichInterceptor = enrichInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor)
                .addPathPatterns("/api/**") //path matching
                .excludePathPatterns("/admin/*")
                .order(1);
        registry.addInterceptor(enrichInterceptor)
                .addPathPatterns("/api/**")
                .order(2);
    }
}
