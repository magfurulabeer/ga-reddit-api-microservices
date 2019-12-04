package com.example.usersapi.service;

import com.example.usersapi.exception.EntityNotFoundException;
import com.example.usersapi.model.User;
import com.example.usersapi.model.UserRole;
import com.example.usersapi.repository.UserRepository;
import com.example.usersapi.repository.UserRoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class UserRoleServiceTests {
    @InjectMocks
    private UserRoleServiceImpl userRoleService;

    @InjectMocks
    private UserRole userRole;

    @InjectMocks
    private User user;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private UserRepository userRepository;

    @Before
    public void initializeDummyVariables() {
        user.setId(1L);
        user.setUsername("username");
        user.setEmail("email@email.com");
        user.setPassword("password");

        userRole.setId(1L);
        userRole.setName("ROLE_ADMIN");
        user.addUserRole(userRole);
    }

    @Test
    public void createUserRole_UserRole_Success() {
        UserRole userRoleRequest = new UserRole();
        userRoleRequest.setName("ROLE_ADMIN");

        when(userRoleRepository.save(any())).thenReturn(userRole);

        UserRole result = userRoleService.createUserRole(userRoleRequest);

        assertNotNull(result);
        assertEquals(result, userRole);
    }

    @Test
    public void searchByUserId_ListOfUserRoles_Success() throws EntityNotFoundException {
        when(userRepository.findById(anyLong())).thenReturn(java.util.Optional.of(user));

        Collection<UserRole> result = userRoleService.searchByUserId(1L);

        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(userRole);

        assertNotNull(result);
        assertEquals(result, userRoles);
    }

    @Test(expected = EntityNotFoundException.class)
    public void searchByUserId_Exception_Failure() throws EntityNotFoundException {
        when(userRepository.findById(anyLong())).thenThrow(NoSuchElementException.class);

        userRoleService.searchByUserId(1L);
    }

    @Test
    public void deleteUserRole_HttpStatusOK_Success() throws EntityNotFoundException {

        HttpStatus result = userRoleService.deleteUserRole(1L);

        assertNotNull(result);
        assertEquals(result, HttpStatus.OK);
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteUserRole_Exception_Failure() throws EntityNotFoundException {
        when(userRoleService.deleteUserRole(anyLong())).thenThrow(NoSuchElementException.class);
        userRoleService.deleteUserRole(1L);
    }
}
