package com.ameda.kev.paginationcursorkeyset.filters.config;

import com.ameda.kev.paginationcursorkeyset.filters.DummyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: kev.Ameda
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<DummyFilter> filterFilterRegistrationBean(){
        FilterRegistrationBean<DummyFilter> registrationBean =
                new FilterRegistrationBean<>();
        registrationBean.setFilter(new DummyFilter());
        registrationBean.setOrder(4);
        //if url patterns match these below then the filter runs else does not run
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }
}
