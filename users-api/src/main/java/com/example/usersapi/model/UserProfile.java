package com.example.usersapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;

/**
 * User Profile entity to represent additional information about our
 * users in our data model. A user profile may have an additional
 * email, a mobile phone number, and an address.
 */
@Entity
@Table(name = "profiles")
public class UserProfile {
    /**
     * Auto Generated Primary Key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Additional E-mail Address of user
     */
    @Column(name = "addl_email")
    @Email
    private String addlEmail;

    /**
     * Mobile Phone number of user
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * Address number of user
     */
    @Column(name = "address")
    private String address;

    /**
     * The user of this profile
     */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Gets id
     * @return {@link #id}
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id
     * @param id a long to represent the primary key for UserProfile
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets additional email
     * @return {@link #addlEmail}
     */
    public String getAddlEmail() {
        return addlEmail;
    }

    /**
     * Sets additional email
     * @param addlEmail a secondary email for the user
     */
    public void setAddlEmail(String addlEmail) {
        this.addlEmail = addlEmail;
    }

    /**
     * Gets mobile phone number
     * @return {@link #mobile}
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets mobile
     * @param mobile the mobile phone number of the user
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Gets address
     * @return {@link #address}
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address
     * @param address text representation of user's physical address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets user
     * @return {@link #user}
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user
     * @param user the user who owns this {@link UserProfile}
     */
    public void setUser(User user) {
        this.user = user;
    }
}
