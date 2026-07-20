package com.ameda.kev.paginationcursorkeyset.service;

import com.ameda.kev.paginationcursorkeyset.dto.UsersDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Author: kev.Ameda
 */
@Service
public class ServiceRestClient {
    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    public ServiceRestClient(RestClient restClient, ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.objectMapper = objectMapper;
    }
    public List<UsersDto> getUsers() {
        JsonNode rootNode = restClient.get()
                .uri("/users")
                .retrieve()
                .body(JsonNode.class);

        if (rootNode == null) {
            return List.of();
        }

        if (rootNode.isObject() && rootNode.has("data")) {
            return objectMapper.convertValue(
                    rootNode.get("data"),
                    new TypeReference<List<UsersDto>>() {}
            );
        }

        if (rootNode.isArray()) {
            return objectMapper.convertValue(
                    rootNode,
                    new TypeReference<List<UsersDto>>() {}
            );
        }

        return List.of();
    }

}
