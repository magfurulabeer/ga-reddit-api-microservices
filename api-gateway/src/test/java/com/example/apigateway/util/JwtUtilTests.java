package com.example.apigateway.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class JwtUtilTests {

    private String jwtToken;

    @InjectMocks
    JwtUtil jwtUtil;

    @Mock
    UserDetails userDetails;

    @Before
    public void init() {
        ReflectionTestUtils.setField(jwtUtil, "secret", "pancakes");
        when(userDetails.getUsername()).thenReturn("ben");
        jwtToken = jwtUtil.generateToken(userDetails);
    }

    @Test
    public void getUserNameFromToken_String_Success(){
        String token = jwtUtil.getUsernameFromToken(jwtToken);
        assertNotNull(token);
    }

    @Test
    public void validateToken_Boolean_Success(){
        Boolean isTrue = jwtUtil.validateToken(jwtToken, userDetails);
        assertNotNull(isTrue);
    }
}