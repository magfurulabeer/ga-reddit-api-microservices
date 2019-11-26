package com.example.usersapi.service;

import com.example.usersapi.exception.EntityNotFoundException;
import com.example.usersapi.model.User;
import com.example.usersapi.model.UserProfile;
import com.example.usersapi.repository.UserProfileRepository;
import com.example.usersapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserProfile searchById(long id) throws EntityNotFoundException {
        try {
            return userProfileRepository.findById(id).get();
        } catch(NoSuchElementException e) {
            throw new EntityNotFoundException("Id does not match any user profile");
        }

    }

//    @Override
//    public HttpStatus deleteUserProfile(long id) {
//        userProfileRepository.deleteById(id);
//        return HttpStatus.OK;
//    }

    @Override
    public UserProfile updateUserProfile(long id, UserProfile userProfileRequest) throws EntityNotFoundException {
        try {
            UserProfile profile = userProfileRepository.findById(id).get();
            profile.setAddlEmail(userProfileRequest.getAddlEmail());
            profile.setAddress(userProfileRequest.getAddress());
            profile.setMobile(userProfileRequest.getMobile());
            return userProfileRepository.save(profile);
        } catch(NoSuchElementException e) {
            throw new EntityNotFoundException("Id does not match any user profile");
        }

    }

    @Override
    public UserProfile createUserProfile(long userId, UserProfile userProfileRequest) throws EntityNotFoundException {
        try {
            User user = userRepository.findById(userId).get();
            userProfileRequest.setUser(user);
            return userProfileRepository.save(userProfileRequest);
        } catch(NoSuchElementException e) {
            throw new EntityNotFoundException("Id does not match any user");
        }

    }
}
