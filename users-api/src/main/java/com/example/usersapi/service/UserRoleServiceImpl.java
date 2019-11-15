package com.example.usersapi.service;

import com.example.usersapi.model.UserRole;
import com.example.usersapi.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public UserRole createUserRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }
}
