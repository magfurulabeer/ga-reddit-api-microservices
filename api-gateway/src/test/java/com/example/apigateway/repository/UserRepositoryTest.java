package com.example.apigateway.repository;

import com.example.apigateway.bean.UserBean;
import com.example.apigateway.bean.UserRoleBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {

    @InjectMocks
    UserRepository repository;

    @Mock
    UserBean user;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Before
    public void init() {
        ReflectionTestUtils.setField(repository, "jdbcTemplate", jdbcTemplate);
    }

    @Test
    public void getUserByUsername_UserBean_Success() {
        HashMap<String, Object> role = new HashMap<>();
        role.put("name", "USER_ROLE_NAME");

        List<Map<String, Object>> userRoleMaps = new ArrayList<Map<String, Object>>(Arrays.asList(role));

        when(jdbcTemplate.queryForObject(any(), any(), any(RowMapper.class))).thenReturn(user);
        when(jdbcTemplate.queryForList(any(), any(Object.class))).thenReturn(userRoleMaps);
        UserBean u = repository.getUserByUsername("username");
        assertEquals(u, user);
    }
}
