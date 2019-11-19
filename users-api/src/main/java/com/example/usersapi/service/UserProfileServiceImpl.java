package com.example.usersapi.service;

import com.example.usersapi.model.User;
import com.example.usersapi.model.UserProfile;
import com.example.usersapi.repository.UserProfileRepository;
import com.example.usersapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserProfile searchById(long id) {
        return userProfileRepository.findById(id).get();
    }

    @Override
    public HttpStatus deleteUserProfile(long id) {
        userProfileRepository.deleteById(id);
        return HttpStatus.OK;
    }

    @Override
    public UserProfile updateUserProfile(long id, UserProfile userProfileRequest) {
        UserProfile profile = userProfileRepository.findById(id).get();
        profile.setAddlEmail(userProfileRequest.getAddlEmail());
        profile.setAddress(userProfileRequest.getAddress());
        profile.setMobile(userProfileRequest.getMobile());
        return userProfileRepository.save(profile);
    }

    @Override
    public UserProfile createUserProfile(long userId, UserProfile userProfileRequest) {
        User user = userRepository.findById(userId).get();
        userProfileRequest.setUser(user);
        return userProfileRepository.save(userProfileRequest);
    }
}
