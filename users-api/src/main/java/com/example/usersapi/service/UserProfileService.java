package com.example.usersapi.service;

import com.example.usersapi.exception.EntityNotFoundException;
import com.example.usersapi.model.UserProfile;
import org.springframework.http.HttpStatus;

public interface UserProfileService {
    public UserProfile searchById(long id) throws EntityNotFoundException;

//    public HttpStatus deleteUserProfile(long id);

    public UserProfile updateUserProfile(long id, UserProfile userProfileRequest) throws EntityNotFoundException;

    public UserProfile createUserProfile(long userId, UserProfile userProfileRequest) throws EntityNotFoundException;
}
