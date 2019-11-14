package com.example.postsapi.service;

import com.example.postsapi.model.Post;
import com.example.postsapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

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

//        @Override
//        public Iterable<Post> searchByName(String name) {
//            String normalized = name.trim().toLowerCase();
//            return postRepository.findByFirstNameContainsOrLastNameContains(normalized, normalized);
//        }

    @Override
    public HttpStatus deletePost(long id) {
        postRepository.deleteById(id);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus createPost(Post post) {
        postRepository.save(post);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus updatePost(long id, Post postRequest) {
        Post post = postRepository.findById(id).get();
        post.setTitle(postRequest.getTitle());
        post.setDescription(postRequest.getDescription());
        postRepository.save(post);
        return HttpStatus.OK;
    }
}

