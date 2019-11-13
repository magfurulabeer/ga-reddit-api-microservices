package com.example.postsapi.controller;

import com.example.postsapi.model.Post;
import com.example.postsapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/list")
    public Iterable<Post> getAll() {
        return postService.getAll();
    }

    @GetMapping("/view/{id}")
    public Post searchById(@PathVariable long id) {
        return postService.searchById(id);
    }

//    @GetMapping("/search/{name}")
//    public Iterable<post> searchByName(@PathVariable String name) {
//        return postService.searchByName(name);
//    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deletePost(@PathVariable long id) {
        return postService.deletePost(id);
    }

//    TODO: Return post
    @PostMapping("/")
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PatchMapping("/update/{id}")
    public HttpStatus updatePost(@PathVariable long id, @RequestBody Post postRequest) {
        return postService.updatePost(id, postRequest);
    }
}
