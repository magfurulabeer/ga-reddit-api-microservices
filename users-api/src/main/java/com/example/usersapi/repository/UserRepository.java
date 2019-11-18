package com.example.usersapi.repository;

import com.example.usersapi.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Gets the user with the given username
     *
     * @param username the username of the desired User
     * @return the User with the given username
     */
    @Query("FROM User u WHERE u.username = ?1")
    public User getUserByUsername(String username);
}
