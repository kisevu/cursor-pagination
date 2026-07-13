package com.ameda.kev.paginationcursorkeyset.converters;

import com.ameda.kev.paginationcursorkeyset.dto.UsersDto;
import com.ameda.kev.paginationcursorkeyset.entities.User;
import org.springframework.stereotype.Component;

/**
 * Author: kev.Ameda
 */
@Component
public class UserConverters {

    public static User userFromDto(UsersDto users){
        return new User().from(users);
    }

    public static UsersDto userToDto(User user){
     return new UsersDto().to(user);
    }

}
