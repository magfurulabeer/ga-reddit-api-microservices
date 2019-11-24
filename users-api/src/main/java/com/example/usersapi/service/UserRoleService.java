package com.example.usersapi.service;

import com.example.usersapi.model.UserRole;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface UserRoleService {
    public UserRole createUserRole(UserRole userRole);

    public List<UserRole> searchByUserId(long userId);

    public HttpStatus deleteUserRole(long id);
}
