package com.example.usersapi.controller;

import com.example.usersapi.model.User;
import com.example.usersapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
//    pass username in header?
    public Iterable<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/view/{id}")
    public User searchById(@PathVariable long id) {
        return userService.searchById(id);
    }

//    @GetMapping("/search/{name}")
//    public Iterable<User> searchByName(@PathVariable String name) {
//        return userService.searchByName(name);
//    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/signup")
    public String createUser(@RequestBody User user) {
        return "hello";
//        return userService.createUser(user);
    }

    @PatchMapping("/update/{id}")
    public HttpStatus updateUser(@PathVariable long id, @RequestBody User userRequest) {
        return userService.updateUser(id, userRequest);
    }
}
