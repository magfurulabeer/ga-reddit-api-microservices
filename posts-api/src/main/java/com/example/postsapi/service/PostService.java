package com.example.postsapi.service;

import com.example.postsapi.model.Post;
import org.springframework.http.HttpStatus;

public interface PostService {

    public Iterable<Post> getAll();

    public Post searchById(long id);

//    public Iterable<User> searchByName(String name);

    public HttpStatus deletePost(long id);

    public HttpStatus createPost(Post post);

    public HttpStatus updatePost(long id, Post postRequest);
}
