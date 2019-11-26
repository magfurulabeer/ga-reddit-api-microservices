package com.example.usersapi.service;

import com.example.usersapi.exception.EntityNotFoundException;
import com.example.usersapi.model.UserRole;
import org.springframework.http.HttpStatus;

import java.util.Collection;

public interface UserRoleService {
    public UserRole createUserRole(UserRole userRole);

    public Collection<UserRole> searchByUserId(long userId) throws EntityNotFoundException;

    public HttpStatus deleteUserRole(long id) throws EntityNotFoundException;
}
