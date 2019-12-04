package com.example.usersapi.controller;

import com.example.usersapi.exception.EntityNotFoundException;
import com.example.usersapi.model.User;
import com.example.usersapi.model.UserProfile;
import com.example.usersapi.service.UserProfileService;
import com.example.usersapi.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {
    private MockMvc mockMvc;

    @InjectMocks
    UserController userController;

    @InjectMocks
    User user;

    @Mock
    UserService userService;

    private static String createUserInJson(String username, String email, String password) {
        return "{ \"username\": \"" + username + "\", \"password\": \"" + password + "\", \"email\": \"" + email + "\" }";
    }

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user.setUsername("username");
        user.setEmail("user@email.com");
        user.setPassword("password");
    }

    @Test
    public void getAll_Users_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/all")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson("username", "user@email.com", "password"));

        Iterable<User> users = new ArrayList<User>(Arrays.asList(user));
        when(userService.getAll()).thenReturn(users);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("[{ \"id\":0, \"username\": \"username\", \"password\": \"password\", \"email\": \"user@email.com\" }]"))
                .andReturn();
    }

    @Test
    public void searchById_User_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/view/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON);

        when(userService.searchById(1)).thenReturn(user);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json(createUserInJson("username", "user@email.com", "password")))
                .andReturn();
    }

    /*
    @PostMapping("/signup")
    public Map<String, String> createUser(@Valid @RequestBody User user) throws DuplicateUserException {
        Map<String, String> result = new HashMap<String,String>();
        result.put("token", userService.createUser(user));
        return result;
    }
     */

    @Test
    public void createUser_User_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson("username", "user@email.com", "password"));

        when(userService.createUser(any())).thenReturn("JWT");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"token\":JWT}"))
                .andReturn();
    }
}
