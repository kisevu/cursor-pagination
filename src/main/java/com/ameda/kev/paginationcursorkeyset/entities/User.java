package com.ameda.kev.paginationcursorkeyset.entities;

import com.ameda.kev.paginationcursorkeyset.dto.UsersDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: kev.Ameda
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tbl_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Long serialNo;
    private String name;
    private String username;
    private String email;
    @Embedded
    private Address address;
    private String phone;
    private String website;
    @Embedded
    private Company company;


    public static User from(UsersDto users){
        return User.builder()
                .serialNo(users.getId())
                .name(users.getName())
                .username(users.getUsername())
                .email(users.getEmail())
                .address(Address.from(users.getAddress()))
                .phone(users.getPhone())
                .website(users.getWebsite())
                .company(Company.from(users.getCompany()))
                .build();
    }


}
