package com.example.usersapi.service;

import com.example.usersapi.model.UserProfile;
import org.springframework.http.HttpStatus;

public interface UserProfileService {
    public UserProfile searchById(long userId);

    public HttpStatus deleteUserProfile(long id);

    public UserProfile updateUserProfile(long userId, UserProfile userProfileRequest);

    public UserProfile createUserProfile(long userId, UserProfile userProfileRequest);
}
