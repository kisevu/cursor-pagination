package com.ameda.kev.paginationcursorkeyset.entities;

import com.ameda.kev.paginationcursorkeyset.dto.GeoDto;
import jakarta.persistence.Embeddable;
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
@Embeddable
public class Geo {
    private String lat;
    private  String lng;

    public static Geo from(GeoDto geoDto){
        return Geo.builder()
                .lat(geoDto.getLat())
                .lng(geoDto.getLng())
                .build();
    }
}
