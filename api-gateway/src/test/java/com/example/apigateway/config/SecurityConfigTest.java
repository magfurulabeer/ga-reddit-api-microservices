package com.example.apigateway.config;

import com.example.apigateway.util.JwtUtil;
import io.jsonwebtoken.Jwts;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class SecurityConfigTest {

//    private MockMvc mockMvc;

//    @Autowired
//    private WebApplicationContext wac;
//
//    @Autowired
//    private FilterChainProxy filterChainProxy;

    @InjectMocks
    SecurityConfig securityConfig;

    @Mock
    private PasswordEncoder bCryptPasswordEncoder;

    @Mock
    AuthenticationManagerBuilder builder;

    private MockHttpServletRequest request = new MockHttpServletRequest();


    @Mock
    HttpServletResponse response;

    @Mock
    InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryAuthentication;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void encoder_PasswordEncoder_Success() {
        PasswordEncoder pe = securityConfig.encoder();
        assertEquals(pe.getClass(), BCryptPasswordEncoder.class);
    }

    @Test
    public void corsConfigurationSource_CorsConfigurationSource_Success() {
        CorsConfigurationSource source = securityConfig.corsConfigurationSource();
        assertEquals(source.getClass(), UrlBasedCorsConfigurationSource.class);
        request.setAttribute("javax.servlet.include.context_path", "/");
        CorsConfiguration config = source.getCorsConfiguration(request);
        assertNotNull(config);
    }

    /*
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication().withUser(users.username("test").password("test").roles("DBA"));
    }
     */

    @Test(expected = Exception.class)
    public void configure_Void_Success() throws Exception {
        securityConfig.configure(builder);
        when(builder.inMemoryAuthentication()).thenReturn(inMemoryAuthentication);

        verify(builder).inMemoryAuthentication();
    }

}
