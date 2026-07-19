package com.ameda.kev.paginationcursorkeyset.filters;


import org.springframework.stereotype.Component;
import java.util.UUID;

/**
 * Author: kev.Ameda
 */
@Component
public class RequestIDGenerator {

    public String generateID(){
        return String.valueOf(System.currentTimeMillis())
                .concat("-"+UUID.randomUUID().toString());
    }

}
