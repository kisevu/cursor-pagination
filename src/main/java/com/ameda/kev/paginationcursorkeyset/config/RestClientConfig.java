package com.ameda.kev.paginationcursorkeyset.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * Author: kev.Ameda
 */
@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient(){
        return RestClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }
}
