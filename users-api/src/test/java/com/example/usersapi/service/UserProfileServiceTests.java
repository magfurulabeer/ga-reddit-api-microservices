package com.example.usersapi.service;

import com.example.usersapi.exception.EntityNotFoundException;
import com.example.usersapi.model.User;
import com.example.usersapi.model.UserProfile;
import com.example.usersapi.repository.UserProfileRepository;
import com.example.usersapi.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileServiceTests {

    @InjectMocks
    private UserProfileServiceImpl userProfileService;

    @InjectMocks
    private UserProfile userProfile;

    @InjectMocks
    private User user;

    @Mock
    private UserProfileRepository userProfileRepository;

    @Mock
    private UserRepository userRepository;

    @Before
    public void initializeDummyVariables() {
        user.setId(1L);
        user.setUsername("username");
        user.setEmail("email@email.com");
        user.setPassword("password");

        userProfile.setId(1L);
        userProfile.setAddlEmail("user@email.com");
        userProfile.setUser(user);
    }

    @Test
    public void createUserProfile_UserProfile_Success() throws EntityNotFoundException {
        UserProfile userProfileRequest = new UserProfile();
        userProfileRequest.setAddlEmail("user@email.com");

        when(userRepository.findById(anyLong())).thenReturn(java.util.Optional.of(user));
        when(userProfileRepository.save(any())).thenReturn(userProfile);

        UserProfile result = userProfileService.createUserProfile(1L, userProfileRequest);

        assertNotNull(result);
        assertEquals(result, userProfile);
    }

    @Test(expected = EntityNotFoundException.class)
    public void createUserProfile_Exception_Failure() throws EntityNotFoundException {
//        when(userProfileRepository.findById(anyLong())).thenThrow(NoSuchElementException.class);
        userProfileService.createUserProfile(1L, userProfile);
    }

    @Test
    public void updateUserProfile_UserProfile_Success() throws EntityNotFoundException {

        UserProfile updatedProfile = userProfile;
        updatedProfile.setAddlEmail("updated@email.com");

        UserProfile userProfileRequest = new UserProfile();
        userProfileRequest.setAddlEmail("updated@email.com");

        when(userProfileRepository.findById(anyLong())).thenReturn(java.util.Optional.of(userProfile));
        when(userProfileRepository.save(any())).thenReturn(updatedProfile);

        UserProfile result = userProfileService.updateUserProfile(1L, userProfileRequest);

        assertNotNull(result);
        assertEquals(result, updatedProfile);
    }

    @Test(expected = EntityNotFoundException.class)
    public void updateUserProfile_Exception_Failure() throws EntityNotFoundException {
        when(userProfileRepository.findById(anyLong())).thenThrow(NoSuchElementException.class);
        userProfileService.updateUserProfile(1L, userProfile);
    }

    @Test
    public void searchById_UserProfile_Success() throws EntityNotFoundException {
        when(userProfileRepository.findById(anyLong())).thenReturn(java.util.Optional.of(userProfile));

        UserProfile result = userProfileService.searchById(1L);

        assertNotNull(result);
        assertEquals(result, userProfile);
    }

    @Test(expected = EntityNotFoundException.class)
    public void searchById_Exception_Failure() throws EntityNotFoundException {
        when(userProfileRepository.findById(anyLong())).thenThrow(NoSuchElementException.class);
        userProfileService.searchById(1L);
    }


}
