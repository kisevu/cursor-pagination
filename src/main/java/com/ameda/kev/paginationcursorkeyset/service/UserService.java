package com.ameda.kev.paginationcursorkeyset.service;

import com.ameda.kev.paginationcursorkeyset.dto.CursorPageResponse;
import com.ameda.kev.paginationcursorkeyset.entities.User;
import java.util.List;

/**
 * Author: kev.Ameda
 */

public interface UserService {
    List<User> addUsers();
    CursorPageResponse<User> fetchNextPage(String cursor, int size);
    User fetchUserById(String id);
}
