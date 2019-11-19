package com.example.usersapi.controller;

import com.example.usersapi.model.User;
import com.example.usersapi.model.UserProfile;
import com.example.usersapi.service.UserProfileService;
import com.example.usersapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/profile/{userId}")
    public UserProfile searchById(@PathVariable long userId) {
        return userProfileService.searchById(userId);
    }


    @DeleteMapping("/profile/{id}")
    public HttpStatus deleteUserProfile(@PathVariable long id) {
        return userProfileService.deleteUserProfile(id);
    }

    @PostMapping("/profile/{userId}")
    public UserProfile createUserProfile(@PathVariable long userId, @RequestBody UserProfile userProfileRequest) {
        return userProfileService.createUserProfile(userId, userProfileRequest);
    }

    @PutMapping("/profile/{userId}")
    public UserProfile updateUserProfile(@PathVariable long userId, @RequestBody UserProfile userProfileRequest) {
        return userProfileService.updateUserProfile(userId, userProfileRequest);
    }
}
