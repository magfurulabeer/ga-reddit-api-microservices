package com.example.usersapi.service;

import com.example.usersapi.model.User;
import com.example.usersapi.model.UserRole;
import com.example.usersapi.repository.UserRepository;
import com.example.usersapi.repository.UserRoleRepository;
import com.example.usersapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public String createUser(User user) {
        user.setPassword(encoder().encode(user.getPassword()));

        UserRole userRole = new UserRole();
        userRole.setName("ROLE_USER");
        userRoleRepository.save(userRole);
        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(userRole);

        user.setUserRoles(userRoles);
        User savedUser = userRepository.save(user);
        if (savedUser != null) {
            String token = jwtUtil.generateToken(savedUser.getUsername());
            return token;

        }
        return null;
    }

    @Override
    public String login(User user) {
        User foundUser = userRepository.getUserByUsername(user.getUsername());
        if(foundUser != null && encoder().matches(user.getPassword(), foundUser.getPassword())) {
            String token = jwtUtil.generateToken(foundUser.getUsername());
            return token;
        }
        return null;
    }

    @Override
    public HttpStatus updateUser(long id, User userRequest) {
        User user = userRepository.findById(id).get();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        userRepository.save(user);
        return HttpStatus.OK;
    }
}

