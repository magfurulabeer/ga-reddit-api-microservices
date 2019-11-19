package com.example.usersapi.repository;

import com.example.usersapi.model.UserProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {
}
