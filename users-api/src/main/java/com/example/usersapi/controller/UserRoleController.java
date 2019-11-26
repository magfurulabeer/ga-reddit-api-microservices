package com.example.usersapi.controller;

import com.example.usersapi.exception.EntityNotFoundException;
import com.example.usersapi.model.UserRole;
import com.example.usersapi.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/role/{userId}")
    public Collection<UserRole> searchByUserId(@PathVariable long userId) throws EntityNotFoundException {
        return userRoleService.searchByUserId(userId);
    }

    @DeleteMapping("/role/{id}")
    public HttpStatus deleteUserRole(@PathVariable long id) throws EntityNotFoundException {
        return userRoleService.deleteUserRole(id);
    }

    @PostMapping("/role")
    public UserRole createUserRole(@RequestBody UserRole userRoleRequest) {
        return userRoleService.createUserRole(userRoleRequest);
    }

}
