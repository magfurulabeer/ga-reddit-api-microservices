package com.example.usersapi.controller;

import com.example.usersapi.model.User;
import com.example.usersapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public Iterable<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/view/{id}")
    public User searchById(@PathVariable long id) {
        return userService.searchById(id);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/signup")
    public Map<String, String> createUser(@RequestBody User user) {
        Map<String, String> result = new HashMap<String,String>();
        result.put("token", userService.createUser(user));
        return result;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        Map<String, String> result = new HashMap<String,String>();
        result.put("token", userService.login(user));
        return result;
    }

    @PatchMapping("/update/{id}")
    public HttpStatus updateUser(@PathVariable long id, @RequestBody User userRequest) {
        return userService.updateUser(id, userRequest);
    }
}
