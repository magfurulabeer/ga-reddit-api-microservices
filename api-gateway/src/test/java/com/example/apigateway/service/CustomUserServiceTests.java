package com.example.apigateway.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.example.apigateway.bean.UserBean;
import com.example.apigateway.bean.UserRoleBean;
import com.example.apigateway.repository.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class CustomUserServiceTests {

    @InjectMocks
    CustomUserService userService;

    @InjectMocks
    private UserBean user;

    @InjectMocks
    private UserRoleBean userRole;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder bCryptPasswordEncoder;

    @Before
    public void init() {
        user.setId(1L);
        user.setUsername("batman");
        user.setPassword("bat");
        user.setEmail("batman@gmail.com");

        userRole.setName("ROLE_USER");
        List<UserRoleBean> roles = new ArrayList<UserRoleBean>(Arrays.asList(userRole));
        user.setUserRoles(roles);
    }

    @Test
    public void loadUserByUsername_UserDetails_Success() {
        when(userRepository.getUserByUsername(anyString())).thenReturn(user);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("password");

        UserDetails userDetails = userService.loadUserByUsername("batman");

        assertEquals(user.getUsername(), userDetails.getUsername());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_UserNotFound_UsernameNotFoundException() {
        when(userRepository.getUserByUsername(anyString())).thenReturn(null);
        userService.loadUserByUsername("batman");
    }
}