package com.example.postsapi.repository;

import com.example.postsapi.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
//    public List<User> findByFirstNameContainsOrLastNameContains(String firstName, String lastName);
}
