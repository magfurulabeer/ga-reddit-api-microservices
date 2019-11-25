package com.example.apigateway.repository;

import com.example.apigateway.bean.UserBean;
import com.example.apigateway.bean.UserRoleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserBean getUserByUsername(String username){
        String userQuery = "SELECT * FROM users WHERE username = ?";
        UserBean user = jdbcTemplate.queryForObject(userQuery, new Object[]{username}, (rs, rowNum) ->
                new UserBean(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password")
                ));

        String userRoleQuery = "SELECT * FROM user_roles WHERE id IN " +
                "(SELECT user_role_id FROM user_role_user WHERE user_id = " +
                "(SELECT id FROM users WHERE username = ?))";
        List<Map<String, Object>> userRoleMaps = jdbcTemplate.queryForList(userRoleQuery, new Object[]{username});
        Collection<UserRoleBean> userRoles = new ArrayList<>();
        for (Map<String, Object> role: userRoleMaps) {
            userRoles.add( new UserRoleBean((String) role.get("name")));
        }

        user.setUserRoles(userRoles);
        return user;
    }
}