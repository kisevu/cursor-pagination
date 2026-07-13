package com.ameda.kev.paginationcursorkeyset.service;

import com.ameda.kev.paginationcursorkeyset.dto.UsersDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * Author: kev.Ameda
 */
@Service
public class ServiceRestClient {

    private final RestClient restClient;

    public ServiceRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

  public List<UsersDto> getUsers(){
        return restClient.get()
                .uri("/users")
                .retrieve()
                .body(new ParameterizedTypeReference<List<UsersDto>>() {});
  }
}
