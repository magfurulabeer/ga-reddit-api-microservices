package com.example.commentsapi.controller;

import com.example.commentsapi.model.Comment;
import com.example.commentsapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

//    TODO: /post/${id}/comment - GET

    @GetMapping("/all")
    public Iterable<Comment> getAll() {
        return commentService.getAll();
    }

    @GetMapping("/view/{id}")
    public Comment searchById(@PathVariable long id) {
        return commentService.searchById(id);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deletecomment(@PathVariable long id) {
        return commentService.deleteComment(id);
    }

//    TODO: attach to post
    @PostMapping("/{postId}")
    public HttpStatus createcomment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    @PatchMapping("/update/{id}")
    public HttpStatus updatecomment(@PathVariable long id, @RequestBody Comment commentRequest) {
        return commentService.updateComment(id, commentRequest);
    }
}
