package com.example.usersapi.service;

import com.example.usersapi.model.UserProfile;
import org.springframework.http.HttpStatus;

public interface UserProfileService {
    public UserProfile searchById(long id);

    public HttpStatus deleteUserProfile(long id);

    public UserProfile updateUserProfile(long id, UserProfile userProfileRequest);

    public UserProfile createUserProfile(long userId, UserProfile userProfileRequest);
}
