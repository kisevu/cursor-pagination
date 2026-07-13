package com.ameda.kev.paginationcursorkeyset.dto;

import com.ameda.kev.paginationcursorkeyset.entities.Geo;
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
public class GeoDto {
    private String lat;
    private  String lng;
    public static GeoDto to(Geo geo){
        return GeoDto.builder()
                .lat(geo.getLat())
                .lng(geo.getLng())
                .build();
    }
}
