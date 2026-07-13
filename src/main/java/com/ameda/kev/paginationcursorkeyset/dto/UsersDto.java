package com.ameda.kev.paginationcursorkeyset.dto;

import com.ameda.kev.paginationcursorkeyset.entities.User;
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
public class UsersDto {
    private Long id;
    private String name;
    private String username;
    private String email;
    private AddressDto address;
    private String phone;
    private String website;
    private CompanyDto company;

    public static UsersDto to(User user){
        return UsersDto.builder()
                .id(user.getSerialNo())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .address(AddressDto.to(user.getAddress()))
                .phone(user.getPhone())
                .website(user.getWebsite())
                .company(CompanyDto.to(user.getCompany()))
                .build();
    }
}
