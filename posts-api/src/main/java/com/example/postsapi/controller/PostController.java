package com.example.postsapi.controller;

import com.example.postsapi.model.Post;
import com.example.postsapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/list")
    public Iterable<Post> getAll() {
        return postService.getAll();
    }

//    @GetMapping("/view/{id}")
//    public Post searchById(@PathVariable long id) {
//        return postService.searchById(id);
//    }

    @DeleteMapping("/{id}")
    public HttpStatus deletePost(@PathVariable long id) throws IOException {
        return postService.deletePost(id);
    }

    @PostMapping("/")
    public Post createPost(@RequestBody Post post, @RequestHeader("username") String username) {
        return postService.createPost(post, username);
    }

    @GetMapping("/{postId}")
    public boolean postWithPostIdExists(@PathVariable long postId) {
        try {
            Post post = postService.searchById(postId);
            return post != null;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    @PatchMapping("/update/{id}")
//    public HttpStatus updatePost(@PathVariable long id, @RequestBody Post postRequest) {
//        return postService.updatePost(id, postRequest);
//    }
}
