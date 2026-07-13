package com.ameda.kev.paginationcursorkeyset.service;

import com.ameda.kev.paginationcursorkeyset.converters.UserConverters;
import com.ameda.kev.paginationcursorkeyset.dto.CursorPageResponse;
import com.ameda.kev.paginationcursorkeyset.dto.UsersDto;
import com.ameda.kev.paginationcursorkeyset.entities.User;
import com.ameda.kev.paginationcursorkeyset.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ServiceRestClient serviceRestClient;

    public UserServiceImpl(UserRepository userRepository,
                           ServiceRestClient serviceRestClient) {
        this.userRepository = userRepository;
        this.serviceRestClient = serviceRestClient;
    }

    @Override
    public List<User> addUsers() {
        List<UsersDto> fetchedUsers = serviceRestClient.getUsers();
        List<User> convertedUsers = new ArrayList<>();
        if (Objects.nonNull(fetchedUsers)){
             convertedUsers = fetchedUsers.stream()
                    .map(UserConverters::userFromDto)
                    .toList();
        }
        return convertedUsers.stream()
                .map(user -> userRepository.save(user))
                .toList();
    }

    @Override
    public CursorPageResponse<User> fetchNextPage(String cursor, int size) {
        // default page = 0,  size = 2; [0-2]
        Pageable pageable = PageRequest.of(0,size);
        List<User> users = userRepository.fetchNextPage(cursor, pageable);
        // check if we have more records
        boolean hasNextPage = users.size() == size;
        //determine the next cursor
        String nextCursor = hasNextPage ? users.get(users.size() - 1).getId() : null;
        return new CursorPageResponse<>(users,size,nextCursor,hasNextPage);
    }
}
