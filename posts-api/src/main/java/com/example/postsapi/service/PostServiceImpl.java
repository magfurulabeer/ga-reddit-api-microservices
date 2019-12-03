package com.example.postsapi.service;

import com.example.postsapi.exception.PostNotFoundException;
import com.example.postsapi.model.Post;
import com.example.postsapi.mq.Sender;
import com.example.postsapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private Sender sender;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Iterable<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Post searchById(long id) throws PostNotFoundException {
        Post p = postRepository
                .findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post with id " + id + " does not exist!"));
        return p;
    }

    @Override
    public HttpStatus deletePost(long id) throws PostNotFoundException {
        sender.send(String.valueOf(id));
        try {
            postRepository.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            throw new PostNotFoundException("Post with id " + id + " does not exist!");
        }
        return HttpStatus.OK;
        // TODO: Add try catch
    }

    @Override
    public Post createPost(Post post, String username) {
        post.setUsername(username);
        return postRepository.save(post);
    }

}

