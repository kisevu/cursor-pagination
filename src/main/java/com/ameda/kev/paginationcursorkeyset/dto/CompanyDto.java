package com.ameda.kev.paginationcursorkeyset.dto;

import com.ameda.kev.paginationcursorkeyset.entities.Company;
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
public class CompanyDto {
    private String companyName;
    private String catchPhrase;
    private String bs;

    public static CompanyDto to(Company company){
        return CompanyDto.builder()
                .companyName(company.getCompanyName())
                .catchPhrase(company.getCatchPhrase())
                .bs(company.getBs())
                .build();
    }
}
