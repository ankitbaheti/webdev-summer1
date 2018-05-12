package com.example.webdev.Repositories;

import com.example.webdev.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
