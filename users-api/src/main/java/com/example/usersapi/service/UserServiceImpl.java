package com.example.usersapi.service;

import com.example.usersapi.exception.DuplicateUserException;
import com.example.usersapi.exception.InvalidCredentialsException;
import com.example.usersapi.model.User;
import com.example.usersapi.model.UserRole;
import com.example.usersapi.repository.UserRepository;
import com.example.usersapi.repository.UserRoleRepository;
import com.example.usersapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User searchById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public HttpStatus deleteUser(long id) {
        userRepository.deleteById(id);
        return HttpStatus.OK;
    }

    @Override
    public String createUser(User user) throws DuplicateUserException, DataIntegrityViolationException {
        // TODO: Remove id from JSON
        try {
            user.setPassword(encoder().encode(user.getPassword()));
            UserRole userRole = new UserRole();
            userRole.setName("ROLE_USER");
            userRoleRepository.save(userRole);
            user.addUserRole(userRole);
            User savedUser = userRepository.save(user);
            String token = jwtUtil.generateToken(savedUser.getUsername());
            return token;
        } catch(DuplicateKeyException e) {
            throw new DuplicateUserException("Email/Username is already taken");
        }
    }

    @Override
    public String login(User user) throws InvalidCredentialsException {
        try {
            User foundUser = userRepository.getUserByUsername(user.getUsername());

            if(!encoder().matches(user.getPassword(), foundUser.getPassword())) {
                throw new AuthenticationException();
            }

            String token = jwtUtil.generateToken(foundUser.getUsername());
            return token;
        } catch(AuthenticationException e) {
            throw new InvalidCredentialsException("Username/Password combination is invalid");
        }
    }

//    @Override
//    public HttpStatus updateUser(long id, User userRequest) {
//        User user = userRepository.findById(id).get();
//        user.setUsername(userRequest.getUsername());
//        user.setEmail(userRequest.getEmail());
//        user.setPassword(userRequest.getPassword());
//        userRepository.save(user);
//        return HttpStatus.OK;
//    }
}

