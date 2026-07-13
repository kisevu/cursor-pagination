package com.ameda.kev.paginationcursorkeyset.repositories;

import com.ameda.kev.paginationcursorkeyset.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: kev.Ameda
 */
@Repository
public interface UserRepository extends JpaRepository<User,String> {


    @Query("""
       SELECT u FROM User u 
       WHERE (:cursor IS NULL OR u.id > :cursor)
       ORDER BY u.id ASC
              """)
    public List<User> fetchNextPage(@Param("cursor") String cursor, Pageable pageable);
}
