package com.example.apigateway.bean;

import java.util.Collection;

public class UserBean {

    private Long id;

    private String email;

    private String username;

    private String password;

    private Collection<UserRoleBean> userRoles;

    public UserBean(Long id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<UserRoleBean> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Collection<UserRoleBean> userRoles) {
        this.userRoles = userRoles;
    }
}
