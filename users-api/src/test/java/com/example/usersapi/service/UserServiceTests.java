package com.example.usersapi.service;

import com.example.usersapi.exception.DuplicateUserException;
import com.example.usersapi.exception.InvalidCredentialsException;
import com.example.usersapi.model.User;
import com.example.usersapi.repository.UserRepository;
import com.example.usersapi.repository.UserRoleRepository;
import com.example.usersapi.util.JwtUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {
    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserRoleRepository userRoleRepository;

    @Mock
    JwtUtil jwtUtil;

    @Mock
    User user;

    @Mock
    User userWithEncodedPassword;

    @Mock
    BCryptPasswordEncoder encoder;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(userService, "userRepository", userRepository);
        ReflectionTestUtils.setField(userService, "userRoleRepository", userRoleRepository);
        ReflectionTestUtils.setField(userService, "jwtUtil", jwtUtil);
    }

    @Test
    public void encoder_BCryptEncoder_Succes() {
        PasswordEncoder encoder = userService.encoder();
        assertEquals(encoder.getClass(), BCryptPasswordEncoder.class);
    }

    @Test
    public void getAll_Users_Success() {
        Iterable<User> users = new ArrayList<User>(Arrays.asList(user));
        when(userRepository.findAll()).thenReturn(users);

        Iterable<User> allUsers = userService.getAll();
        assertEquals(allUsers, users);
    }

    @Test
    public void searchById_User_Success() {
        Iterable<User> users = new ArrayList<User>(Arrays.asList(user));
        when(userRepository.findById(anyLong())).thenReturn(java.util.Optional.of(user));

        User foundUser = userService.searchById(1L);
        assertEquals(foundUser, user);
    }


    @Test
    public void deleteUser_OK_Success() {
        HttpStatus status = userService.deleteUser(1L);
        assertEquals(status, HttpStatus.OK);
    }

    @Test
    public void createUser_Token_Success() throws DuplicateUserException {
        when(user.getPassword()).thenReturn("password");
        when(user.getUsername()).thenReturn("username");
        when(userRepository.save(any())).thenReturn(user);
        when(jwtUtil.generateToken(anyString())).thenReturn("token");
        String token = userService.createUser(user);
        assertEquals(token, "token");
    }

    @Test(expected = DuplicateUserException.class)
    public void createUser_Exception_Failure() throws DuplicateUserException {
        when(user.getPassword()).thenReturn("password");
        when(user.getUsername()).thenReturn("username");
        when(userRepository.save(any())).thenReturn(user);
        when(user.getPassword()).thenReturn("password");
        when(jwtUtil.generateToken(anyString())).thenThrow(DuplicateKeyException.class);
        userService.createUser(user);
    }

    @Test
    public void login_User_Success() throws InvalidCredentialsException {
        PasswordEncoder pe = new BCryptPasswordEncoder();

        when(userRepository.getUserByUsername(any())).thenReturn(userWithEncodedPassword);
        when(user.getUsername()).thenReturn("username");
        when(user.getPassword()).thenReturn("password");
        when(jwtUtil.generateToken(any())).thenReturn("token");
        when(userWithEncodedPassword.getPassword()).thenReturn(pe.encode("password"));

        String token = userService.login(user);
        assertEquals("token", token);
    }

    @Test(expected = InvalidCredentialsException.class)
    public void login_Exception_Failure() throws InvalidCredentialsException {

        when(userRepository.getUserByUsername(any())).thenReturn(user);
        when(user.getUsername()).thenReturn("username");
        when(user.getPassword()).thenReturn("password");

        userService.login(user);
    }
}
