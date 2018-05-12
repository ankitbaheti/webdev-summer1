package com.example.webdev.Services;

import com.example.webdev.Models.User;
import com.example.webdev.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserService {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/api/user")
    public List<User> findAllUser(){
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/api/user")
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @DeleteMapping("/api/user/{userId}")
    public void deleteUser(@PathVariable("userId") int userId){
        userRepository.deleteById(userId);
    }

    @PutMapping("/api/user/{userId}")
    public User updateUser(@PathVariable("userId") int userId,
                           @RequestBody User user){
        if(userRepository.findById(userId).isPresent()){
            User user1 = userRepository.findById(userId).get();
            user1.setUser(user);
            return userRepository.save(user1);
        }
        return null;
    }
}
