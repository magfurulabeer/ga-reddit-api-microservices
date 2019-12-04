package com.example.apigateway.util;

import com.example.apigateway.bean.UserRoleBean;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class JwtUtilTests {

    @InjectMocks
    JwtUtil jwtUtil;

    @Mock
    UserDetails details;

    @Mock
    Jwts jwts;

    @Mock
    JwtBuilder builder;

    @Test
    public void generateToken_String_Success() {
        when(details.getUsername()).thenReturn("username");
        when(Jwts.builder()).thenReturn(builder);
        when(builder.setClaims(any())).thenReturn(Jwts.builder());
        when(builder.compact()).thenReturn("token");
        String token = jwtUtil.generateToken(details);
        assertEquals(token, "token");
    }
}
