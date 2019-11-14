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

    @GetMapping("/{postId}")
    public Iterable<Comment> getCommentsByPostId(@PathVariable long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/view/{id}")
    public Comment searchById(@PathVariable long id) {
        return commentService.searchById(id);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deletecomment(@PathVariable long id) {
        return commentService.deleteComment(id);
    }

    @PostMapping("/{postId}")
    public Comment createcomment(@RequestBody Comment comment, @PathVariable long postId, @RequestHeader("username") String username) {
        return commentService.createComment(comment, postId, username);
    }

    @PatchMapping("/update/{id}")
    public HttpStatus updatecomment(@PathVariable long id, @RequestBody Comment commentRequest) {
        return commentService.updateComment(id, commentRequest);
    }

    @DeleteMapping("/posts/{postId}")
    public HttpStatus deleteCommentsByPostId(@PathVariable long postId) {
        return commentService.deleteCommentsByPostId(postId);
    }
}
