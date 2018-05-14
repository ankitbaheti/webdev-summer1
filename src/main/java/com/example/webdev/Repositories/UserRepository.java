package com.example.webdev.Repositories;

import com.example.webdev.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u from User u WHERE u.username=:username")
    Iterable<User> findUserByUsername(@Param("username") String u);
}
