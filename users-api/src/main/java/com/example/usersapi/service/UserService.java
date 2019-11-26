package com.example.usersapi.service;

import com.example.usersapi.exception.DuplicateUserException;
import com.example.usersapi.exception.InvalidCredentialsException;
import com.example.usersapi.model.User;
import org.springframework.http.HttpStatus;

public interface UserService {

    public Iterable<User> getAll();

    public User searchById(long id);

//    public Iterable<User> searchByName(String name);

    public HttpStatus deleteUser(long id);

    public String createUser(User user) throws DuplicateUserException;

    public String login(User user) throws InvalidCredentialsException;

//    public HttpStatus updateUser(long id, User userRequest);
}
