package com.ameda.kev.paginationcursorkeyset.dto;

import com.ameda.kev.paginationcursorkeyset.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: kev.Ameda
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoDto geo;

    public static AddressDto to(Address address){
        return AddressDto.builder()
                .street(address.getStreet())
                .suite(address.getSuite())
                .city(address.getCity())
                .zipcode(address.getZipcode())
                .geo(GeoDto.to(address.getGeo()))
                .build();
    }
}
