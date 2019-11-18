package com.example.usersapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * User Role entity to represent containers of authorities and
 * privileges the end user is given. A user role has a name and
 * a n:m relationship to User.
 */
@Entity
@Table(name = "user_roles")
public class UserRole {
    /**
     * Auto Generated Primary Key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // TODO: Shouldn't this be unique? Any harm in duplication?
    /**
     * Auto Generated Primary Key
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * n:m relation to {@link User}
     */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "user_role_user",
        joinColumns = {@JoinColumn(name = "user_role_id")},
        inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> users;

    /**
     * Gets id
     * @return {@link #id}
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id
     * @param id a long to represent the primary key for UserRole
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets name
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name
     * @param name the name of the user role which should be uppercase and should start with "USER_"
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets list of users
     * @return {@link #users}
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Sets user
     * @param users list of users with this user role
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
