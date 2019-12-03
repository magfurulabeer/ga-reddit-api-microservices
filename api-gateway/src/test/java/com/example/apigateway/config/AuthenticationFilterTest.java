package com.example.apigateway.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
//import org.springframework.mock.web.
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationFilterTest {

    private AuthenticationFilter authenticationFilter = new AuthenticationFilter();

    @Test
    public void filterType_String_Success() {
        String type = authenticationFilter.filterType();
        assertEquals(type, "pre");
    }

    @Test
    public void filterOrder_Int_Succes() {
        int order = authenticationFilter.filterOrder();
        assertEquals(order, 1);
    }

    @Test
    public void shouldFilter_Boolean_Success() {
        boolean should = authenticationFilter.shouldFilter();
        assertEquals(should, true);
    }

    @Test
    public void run_UpdatedContext_Success() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        Object val = ctx.getZuulRequestHeaders().get("username");
        String un = SecurityContextHolder.getContext().getAuthentication().getName();
        assertEquals(val, un);
        assertEquals(authenticationFilter.run(), null);
    }
}