package com.ameda.kev.paginationcursorkeyset.resource;

import com.ameda.kev.paginationcursorkeyset.dto.CursorPageResponse;
import com.ameda.kev.paginationcursorkeyset.entities.User;
import com.ameda.kev.paginationcursorkeyset.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Author: kev.Ameda
 */
@RestController
@RequestMapping("/api/rate-limited")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @PostMapping("/add-users")
    public ResponseEntity<?> addUsers(){
        return new ResponseEntity<>( userService.addUsers(), HttpStatus.CREATED);
    }

    @GetMapping("/get-users")
    public ResponseEntity<CursorPageResponse<User>> getUsers(@RequestParam(required = false) String cursor,
                                                             @RequestParam(defaultValue = "2") int size){
        return ResponseEntity.ok().body(userService.fetchNextPage(cursor,size));
    }

    @GetMapping("/get-user")
    public ResponseEntity<User> getUser(@RequestParam(required = true) String id){
        return ResponseEntity.ok()
                .body(userService.fetchUserById(id));
    }
}
