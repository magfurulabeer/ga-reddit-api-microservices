package com.example.commentsapi.service;

import com.example.commentsapi.exception.CommentNotFoundException;
import com.example.commentsapi.exception.PostNotFoundException;
import com.example.commentsapi.feign.PostsClient;
import com.example.commentsapi.model.Comment;
import com.example.commentsapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    PostsClient postsClient;

    @Override
    public Iterable<Comment> getCommentsByPostId(long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @Override
    public Comment searchById(long id) {
        return commentRepository.findById(id).get();
    }

//        @Override
//        public Iterable<Comment> searchByName(String name) {
//            String normalized = name.trim().toLowerCase();
//            return commentRepository.findByFirstNameContainsOrLastNameContains(normalized, normalized);
//        }

    @Override
    public HttpStatus deleteComment(long id) throws CommentNotFoundException {
        try {
            commentRepository.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            throw new CommentNotFoundException("Comment with id " + id + " does not exist!");
        }
        return HttpStatus.OK;
    }

    @Override
    public Comment createComment(Comment comment, long postId, String username) throws PostNotFoundException {
        if(!postsClient.postWithPostIdExists(postId)) {
            // TODO: Throw Custom Exception
            throw new PostNotFoundException("Post with id " + postId + " does not exist!");
        }
        comment.setPostId(postId);
        comment.setUsername(username);
        return commentRepository.save(comment);
    }

//    @Override
//    public HttpStatus updateComment(long id, Comment commentRequest) {
//        Comment comment = commentRepository.findById(id).get();
//        comment.setText(commentRequest.getText());
//        commentRepository.save(comment);
//        return HttpStatus.OK;
//    }

    @Override
    public HttpStatus deleteCommentsByPostId(long postId) {
        commentRepository.deleteAllByPostId(postId);
        return HttpStatus.OK;
    }
}

