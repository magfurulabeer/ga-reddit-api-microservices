package com.example.commentsapi.service;

import com.example.commentsapi.model.Comment;
import org.springframework.http.HttpStatus;

public interface CommentService {

    public Iterable<Comment> getAll();

    public Comment searchById(long id);

//    public Iterable<User> searchByName(String name);

    public HttpStatus deleteComment(long id);

    public HttpStatus createComment(Comment comment);

    public HttpStatus updateComment(long id, Comment commentRequest);
}
