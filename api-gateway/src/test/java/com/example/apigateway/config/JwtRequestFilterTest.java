package com.example.apigateway.config;

import com.example.apigateway.service.CustomUserService;
import com.example.apigateway.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import sun.reflect.Reflection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.*;

@RunWith (SpringJUnit4ClassRunner.class)
public class JwtRequestFilterTest
{

    @InjectMocks
    private JwtRequestFilter requestFilter;

    @Mock
    CustomUserService userService;

    @Mock
    JwtUtil jwtUtil;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    FilterChain chain;

    @Mock
    UserDetails userDetails;

    @Before
    public void setup(){
        ReflectionTestUtils.setField(requestFilter, "userService", userService);
        ReflectionTestUtils.setField(requestFilter, "jwtUtil", jwtUtil);
    }

    @After
    public void reset_mocks() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Test
    public void doFilterInternal_Void_Success() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn("/login");
        when(request.getHeader(any())).thenReturn("Bearer JWT");
        when(jwtUtil.getUsernameFromToken(any())).thenReturn("username");
        when(userService.loadUserByUsername(any())).thenReturn(userDetails);
        when(jwtUtil.validateToken(any(), any())).thenReturn(true);
        when(userDetails.getAuthorities()).thenReturn(new ArrayList());

        requestFilter.doFilterInternal(request, response, chain);

        verify(request).getHeader("Authorization");

    }

    @Test
    public void doFilterInternal_ExpiredJwtException_Failure() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn("/login");
        when(request.getHeader(any())).thenReturn("Bearer JWT");
        when(jwtUtil.getUsernameFromToken(any())).thenThrow(ExpiredJwtException.class);
        when(userService.loadUserByUsername(any())).thenReturn(userDetails);
        when(jwtUtil.validateToken(any(), any())).thenReturn(true);
        when(userDetails.getAuthorities()).thenReturn(new ArrayList());

        requestFilter.doFilterInternal(request, response, chain);

        verify(request).getHeader("Authorization");

    }

    @Test
    public void doFilterInternal_IllegalArgumentException_Failure() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn("/login");
        when(request.getHeader(any())).thenReturn("Bearer JWT");
        when(jwtUtil.getUsernameFromToken(any())).thenThrow(IllegalArgumentException.class);
        when(userService.loadUserByUsername(any())).thenReturn(userDetails);
        when(jwtUtil.validateToken(any(), any())).thenReturn(true);
        when(userDetails.getAuthorities()).thenReturn(new ArrayList());

        requestFilter.doFilterInternal(request, response, chain);

        verify(request).getHeader("Authorization");
    }


    @Test
    public void doFilterInternal_MissingBearer_Failure() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn("/login");
        when(request.getHeader(any())).thenReturn("JWT");
        when(jwtUtil.getUsernameFromToken(any())).thenReturn("username");
        when(userService.loadUserByUsername(any())).thenReturn(userDetails);
        when(jwtUtil.validateToken(any(), any())).thenReturn(true);
        when(userDetails.getAuthorities()).thenReturn(new ArrayList());

        requestFilter.doFilterInternal(request, response, chain);

        verify(request).getHeader("Authorization");

    }
}