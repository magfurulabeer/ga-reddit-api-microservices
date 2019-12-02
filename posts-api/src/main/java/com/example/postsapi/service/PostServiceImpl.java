package com.example.postsapi.service;

import com.example.postsapi.model.Post;
import com.example.postsapi.mq.Sender;
import com.example.postsapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
    public Post searchById(long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public HttpStatus deletePost(long id) throws IOException {
        sender.send(String.valueOf(id));
        postRepository.deleteById(id);
        return HttpStatus.OK;
        // TODO: Add try catch
    }

    @Override
    public Post createPost(Post post, String username) {
        post.setUsername(username);
        return postRepository.save(post);
    }

//    @Override
//    public HttpStatus updatePost(long id, Post postRequest) {
//        Post post = postRepository.findById(id).get();
//        post.setTitle(postRequest.getTitle());
//        post.setDescription(postRequest.getDescription());
//        postRepository.save(post);
//        return HttpStatus.OK;
//    }
}

