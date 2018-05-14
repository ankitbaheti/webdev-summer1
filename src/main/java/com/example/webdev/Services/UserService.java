package com.example.webdev.Services;

import com.example.webdev.Models.User;
import com.example.webdev.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UserService {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/api/user")
    public List<User> findAllUser(){
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/api/user/{userId}")
    public User findUserById(@PathVariable("userId") int usedId){
        if(userRepository.findById(usedId).isPresent())
            return userRepository.findById(usedId).get();
        return null;
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

    public User findUserByUsername(String username){
        List<User> users = (List<User>) userRepository.findUserByUsername(username);
        if(users.size()!=0){
            return users.get(0);
        }
        return null;
    }

    @PostMapping("/api/register")
    public User register(@RequestBody User user, HttpServletResponse httpServletResponse){
        User user1 = findUserByUsername(user.getUsername());
        if(user1 == null){
            return userRepository.save(user);
        }
        else{
            httpServletResponse.setStatus(HttpServletResponse.SC_CONFLICT);
            return null;
        }
    }

    public User findUserByCredential(String username, String password){
        System.out.println("hello from credential");
        List<User> users = (List<User>) userRepository.findUserByCredential(username, password);
        if(users.size() != 0) {
            return users.get(0);
        }
        return null;
    }

    @PostMapping("/api/login")
    public User login(@RequestBody User user, HttpServletResponse httpServletResponse){
        System.out.println("hello from login");
        User user1 = findUserByCredential(user.getUsername(), user.getPassword());
        if(user1 != null){
            return user1;
        }
        else{
            httpServletResponse.setStatus(HttpServletResponse.SC_CONFLICT);
            return null;
        }
    }
}
