package com.example.apigateway.bean;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserBeanTest {

    @InjectMocks
    UserBean user;

    @Before
    public void init() {
        user.setId(1L);
        user.setEmail("email");
        user.setUsername("username");
        user.setPassword("password");
    }

    @Test
    public void getId_Long_Success() {
        assertEquals(1L, (long) user.getId());
    }

    @Test
    public void getEmail_String_Success() {
        assertEquals("email", user.getEmail());
    }
}
