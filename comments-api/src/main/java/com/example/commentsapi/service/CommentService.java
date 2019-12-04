package com.example.commentsapi.service;

import com.example.commentsapi.exception.CommentNotFoundException;
import com.example.commentsapi.exception.PostNotFoundException;
import com.example.commentsapi.model.Comment;
import org.springframework.http.HttpStatus;

public interface CommentService {

    public Iterable<Comment> getCommentsByPostId(long postId);

    public Comment searchById(long id);

//    public Iterable<User> searchByName(String name);

    public HttpStatus deleteComment(long id) throws CommentNotFoundException;

    public Comment createComment(Comment comment, long postId, String username) throws PostNotFoundException;

//    public HttpStatus updateComment(long id, Comment commentRequest);

    public HttpStatus deleteCommentsByPostId(long postId);
}
