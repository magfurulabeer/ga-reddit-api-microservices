package com.example.usersapi.service;

import com.example.usersapi.model.UserRole;

public class UserRoleServiceImpl implements UserRoleService {
    @Override
    public UserRole createUserRole(UserRole userRole) {
        return UserRoleRepository.save(userRole);
    }
}
