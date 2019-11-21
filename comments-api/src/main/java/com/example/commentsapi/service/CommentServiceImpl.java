package com.example.commentsapi.service;

import com.example.commentsapi.feign.PostsClient;
import com.example.commentsapi.model.Comment;
import com.example.commentsapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public HttpStatus deleteComment(long id) {
        commentRepository.deleteById(id);
        return HttpStatus.OK;
    }

    @Override
    public Comment createComment(Comment comment, long postId, String username) throws Exception {
        if(!postsClient.postWithPostIdExists(postId)) {
            throw new Exception("Post with id " + postId + " does not exist!");
        }
        comment.setPostId(postId);
        comment.setUsername(username);
        return commentRepository.save(comment);
    }

    @Override
    public HttpStatus updateComment(long id, Comment commentRequest) {
        Comment comment = commentRepository.findById(id).get();
        comment.setText(commentRequest.getText());
        commentRepository.save(comment);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus deleteCommentsByPostId(long postId) {
        commentRepository.deleteAllByPostId(postId);
        System.out.println("<><><><><><><><");
        return HttpStatus.OK;
    }
}

