package com.example.usersapi.service;

import com.example.usersapi.exception.EntityNotFoundException;
import com.example.usersapi.model.User;
import com.example.usersapi.model.UserRole;
import com.example.usersapi.repository.UserRepository;
import com.example.usersapi.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserRole createUserRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public Collection<UserRole> searchByUserId(long userId) throws EntityNotFoundException {
        try {
            User user = userRepository.findById(userId).get();
            return user.getUserRoles();
        } catch(NoSuchElementException e) {
            throw new EntityNotFoundException("Id does not match any user");
        }

    }

    @Override
    public HttpStatus deleteUserRole(long id) throws EntityNotFoundException {
        try {
            userRoleRepository.deleteById(id);
            return HttpStatus.OK;
        } catch(NoSuchElementException e) {
            throw new EntityNotFoundException("Id does not match any user profile");
        }

    }
}
