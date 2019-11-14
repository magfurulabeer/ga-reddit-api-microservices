package com.example.commentsapi.repository;

import com.example.commentsapi.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    @Transactional
    public void deleteAllByPostId(Long postId);

    public Iterable<Comment> findAllByPostId(Long postId);
}
