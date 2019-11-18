package com.example.usersapi.service;

import com.example.usersapi.model.UserRole;

public interface UserRoleService {
    /**
     * Creates and saves the given user to the database
     *
     * @param userRole the UserRole object that needs to be saved to the database
     * @return the saved UserRole if successful
     */
    public UserRole createUserRole(UserRole userRole);
}
