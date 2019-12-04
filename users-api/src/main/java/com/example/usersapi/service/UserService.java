package com.example.usersapi.service;

import com.example.usersapi.exception.DuplicateUserException;
import com.example.usersapi.exception.InvalidCredentialsException;
import com.example.usersapi.model.User;
import org.springframework.http.HttpStatus;

public interface UserService {

    /**
     * Gets a list of all users
     *
     * @return an iterable containing all users
     */
    public Iterable<User> getAll();

    /**
     * Returns the user with the given id
     *
     * @param id  the id of the user
     * @return the User with the given id
     */
    public User searchById(long id);

    /**
     * Deletes the user with the given id
     *
     * @param id  the id of the user
     * @return an HttpStatus representing the success or failure of the operation
     */
    public HttpStatus deleteUser(long id);

    /**
     * Creates and saves the given user to the database
     *
     * @param user the user object that needs to be saved to the database
     * @return the a JWT to be used for authentication purposes
     */
    public String createUser(User user) throws DuplicateUserException;

    /**
     * Logs in using username and password
     *
     * @param user the user object that needs to be saved to the database
     * @return the a JWT to be used for authentication purposes
     */
    public String login(User user) throws InvalidCredentialsException;
}
