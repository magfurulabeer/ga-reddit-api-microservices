package com.example.apigateway.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.example.apigateway.bean.UserBean;
import com.example.apigateway.repository.UserRepository;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
//import org.springframework.mock.web.
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationFilterTest {

    @InjectMocks
    AuthenticationFilter authenticationFilter;

    @InjectMocks
    UserBean user;

    @Mock
    SecurityContext securityContext;

    @Mock
    Authentication authentication;

    @Before
    public void init(){
        SecurityContextHolder.setContext(securityContext);
        user.setEmail("email");
        user.setUsername("username");
        user.setPassword("password");
    }

    @Test
    public void filterType_String_Success() {
        String type = authenticationFilter.filterType();
        assertEquals(type, "pre");
    }

    @Test
    public void filterOrder_Int_Success() {
        int order = authenticationFilter.filterOrder();
        assertEquals(order, 1);
    }

    @Test
    public void shouldFilter_Boolean_Success() {
        boolean should = authenticationFilter.shouldFilter();
        assertEquals(should, true);
    }

    @Test
    public void run_Void_Success() throws ZuulException {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("username");

        authenticationFilter.run();
    }
}