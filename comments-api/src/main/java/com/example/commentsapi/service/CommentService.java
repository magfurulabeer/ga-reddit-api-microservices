package com.example.commentsapi.service;

import com.example.commentsapi.model.Comment;
import org.springframework.http.HttpStatus;

public interface CommentService {

    public Iterable<Comment> getCommentsByPostId(long postId);

    public Comment searchById(long id);

//    public Iterable<User> searchByName(String name);

    public HttpStatus deleteComment(long id);

    public Comment createComment(Comment comment, long postId, String username) throws Exception;

    public HttpStatus updateComment(long id, Comment commentRequest);

    public HttpStatus deleteCommentsByPostId(long postId);
}
