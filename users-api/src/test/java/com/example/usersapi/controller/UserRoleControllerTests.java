package com.example.usersapi.controller;

import com.example.usersapi.exception.EntityNotFoundException;
import com.example.usersapi.model.User;
import com.example.usersapi.model.UserProfile;
import com.example.usersapi.model.UserRole;
import com.example.usersapi.service.UserProfileService;
import com.example.usersapi.service.UserRoleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class UserRoleControllerTests {
    private MockMvc mockMvc;

    @InjectMocks
    private UserRoleController userRoleController;

    @InjectMocks
    private UserRole userRole;

    @Mock
    private UserRoleService userRoleService;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userRoleController).build();
    }

    @Before
    public void initializeDummyUserRole() {
        userRole.setId(1L);
        userRole.setName("ROLE_ADMIN");
    }

    private static String createUserRoleInJson(String name) {
        return "{ \"name\": \"" + name + "\"}";
    }

    @Test
    public void createUserRole_UserRole_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/role")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserRoleInJson("ROLE_ADMIN"));

        when(userRoleService.createUserRole(any())).thenReturn(userRole);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"ROLE_ADMIN\"}"))
                .andReturn();
    }

    @Test
    public void searchByUserId_ListOfUserRoles_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/role/1")
                .contentType(MediaType.APPLICATION_JSON);

        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(userRole);

        when(userRoleService.searchByUserId(anyLong())).thenReturn(userRoles);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"ROLE_ADMIN\"}]"))
                .andReturn();
    }

    @Test
    public void deleteUserRole_HttpStatusOK_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/role/1")
                .contentType(MediaType.APPLICATION_JSON);

        when(userRoleService.deleteUserRole(anyLong())).thenReturn(HttpStatus.OK);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }
}
