package com.ameda.kev.paginationcursorkeyset.entities;

import com.ameda.kev.paginationcursorkeyset.dto.CompanyDto;
import jakarta.persistence.Embeddable;
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
public class Company {
    private String companyName;
    private String catchPhrase;
    private String bs;

    public static Company from(CompanyDto companyDto){
        return Company.builder()
                .companyName(companyDto.getCompanyName())
                .catchPhrase(companyDto.getCatchPhrase())
                .bs(companyDto.getBs())
                .build();
    }
}
