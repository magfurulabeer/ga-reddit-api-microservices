package com.example.usersapi.controller;

import com.example.usersapi.exception.EntityNotFoundException;
import com.example.usersapi.model.User;
import com.example.usersapi.model.UserProfile;
import com.example.usersapi.service.UserProfileService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class UserProfileControllerTests {
    private MockMvc mockMvc;

    @InjectMocks
    private UserProfileController userProfileController;

    @InjectMocks
    private UserProfile userProfile;

    @Mock
    private UserProfileService userProfileService;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userProfileController).build();
    }

    @Before
    public void initializeDummyUserProfile() {
        userProfile.setId(1L);
        userProfile.setAddlEmail("user@email.com");
    }

    private static String createUserProfileInJson(String addlEmail) {
        return "{ \"addlEmail\": \"" + addlEmail + "\"}";
    }

    @Test
    public void createUserProfile_UserProfile_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/profile/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserProfileInJson("user@email.com"));

        when(userProfileService.createUserProfile((anyLong()), any())).thenReturn(userProfile);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"addlEmail\":\"user@email.com\",\"mobile\":null,\"address\":null}"))
                .andReturn();
    }

    @Test
    public void searchById_UserProfile_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/profile/1")
                .contentType(MediaType.APPLICATION_JSON);

        when(userProfileService.searchById(anyLong())).thenReturn(userProfile);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"addlEmail\":\"user@email.com\",\"mobile\":null,\"address\":null}"))
                .andReturn();
    }

    @Test
    public void updateUserProfile_UserProfile_Success() throws Exception {
        UserProfile updatedProfile = userProfile;
        updatedProfile.setAddlEmail("updated@email.com");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/profile/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserProfileInJson("updated@email.com"));

        when(userProfileService.updateUserProfile(anyLong(), any())).thenReturn(updatedProfile);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"addlEmail\":\"updated@email.com\",\"mobile\":null,\"address\":null}"))
                .andReturn();
    }
}
