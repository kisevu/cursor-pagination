package com.ameda.kev.paginationcursorkeyset.entities;

import com.ameda.kev.paginationcursorkeyset.dto.AddressDto;
import com.ameda.kev.paginationcursorkeyset.dto.GeoDto;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: kev.Ameda
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    @Embedded
    private Geo geo;
    public static Address from(AddressDto addressDto){
        return Address.builder()
                .street(addressDto.getStreet())
                .suite(addressDto.getSuite())
                .city(addressDto.getCity())
                .zipcode(addressDto.getZipcode())
                .geo(Geo.from(addressDto.getGeo()))
                .build();
    }
}
