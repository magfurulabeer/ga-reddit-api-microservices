package com.example.usersapi.service;

import com.example.usersapi.exception.EntityNotFoundException;
import com.example.usersapi.model.UserRole;
import org.springframework.http.HttpStatus;

import java.util.Collection;

public interface UserRoleService {
    /**
     * Creates and saves the given user to the database
     *
     * @param userRole the UserRole object that needs to be saved to the database
     * @return the saved UserRole if successful
     */
    public UserRole createUserRole(UserRole userRole);

    public Collection<UserRole> searchByUserId(long userId) throws EntityNotFoundException;

    public HttpStatus deleteUserRole(long id) throws EntityNotFoundException;
}
