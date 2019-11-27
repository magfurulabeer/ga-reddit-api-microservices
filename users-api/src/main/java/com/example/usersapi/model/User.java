package com.example.usersapi.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User entity to represent end users in our data model. A user has
 * a unique username, a unique email, and an encoded password. Every
 * user has a UserProfile and one or more UserRoles.
 */
@Entity
@Table(name = "users")
public class User {
    /**
     * Auto Generated Primary Key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Unique identifier for use by the end user.
     */
    @NotEmpty
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /**
     * E-mail address of end user
     */
    @NotEmpty
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * Encoded password for the user to log in.
     */
    @NotEmpty
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Optional UserProfile if end user wishes to add more information
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private UserProfile profile;

    /**
     * User Roles the end user possesses for authorization purposes
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "user_role_user",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_role_id")})
    private Collection<UserRole> userRoles;

    /**
     * Default Constructor
     */
    public User() {}

    /**
     * Convenience Constructor for testing
     */
    public User(long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * Gets id
     * @return {@link #id}
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id
     * @param id a long to represent the primary key for User
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets username
     * @return {@link #username}
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username
     * @param username username for login
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets email
     * @return {@link #email}
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email
     * @param email user's email for communication
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password
     * @return {@link #password}
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password
     * @param password password for login
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets user profile
     * @return {@link #profile}
     */
    public UserProfile getProfile() {
        return profile;
    }

    /**
     * Sets user profile
     * @param profile user profile for end user
     */
    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    /**
     * Gets list of user roles
     * @return {@link #userRoles}
     */
    public Collection<UserRole> getUserRoles() {
        return userRoles;
    }

    /**
     * Sets user roles
     * @param userRoles list of user roles the end user possesses
     */
    public void setUserRoles(Collection<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    /**
     * Adds a userRole to the Users userRoles collection
     * @param userRole list of user roles the end user possesses
     */
    public Collection<UserRole> addUserRole(UserRole userRole) {
        if (this.userRoles == null) {
            this.userRoles = new ArrayList<>();
        }
        this.userRoles.add(userRole);
        return this.userRoles;
    }
}
