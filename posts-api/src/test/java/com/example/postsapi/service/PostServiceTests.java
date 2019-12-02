package com.example.postsapi.service;

import com.example.postsapi.model.Post;
import com.example.postsapi.mq.Sender;
import com.example.postsapi.repository.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTests {

    @InjectMocks
    PostServiceImpl postService;

    @InjectMocks
    private Post post;

    @Mock
    private PostRepository postRepository;

    @Mock
    private Sender sender;

    @Before
    public void initializeDummyPost() {
        post.setId(1L);
        post.setTitle("title");
        post.setDescription("description");
        post.setUsername("username");
    }

    @Test
    public void createPost_Post_Success() {
        Post postRequest = new Post();
        postRequest.setTitle("title");
        postRequest.setDescription("description");

        when(postRepository.save(any())).thenReturn(post);

        Post result = postService.createPost(postRequest, "username");

        assertNotNull(result);
        assertEquals(result, post);
    }

    @Test
    public void getAll_ListOfPosts_Success() {
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        when(postRepository.findAll()).thenReturn(posts);
        Iterable<Post> result = postService.getAll();

        assertNotNull(result);
        assertEquals(result, posts);
    }

    @Test
    public void searchById_Post_Success() {
        when(postRepository.findById(any())).thenReturn(java.util.Optional.of(post));
        Post result = postService.searchById(1L);

        assertNotNull(result);
        assertEquals(result, post);
    }

    @Test
    public void deletePost_Post_Success() throws Exception {
        HttpStatus result = postService.deletePost(1L);

        assertNotNull(result);
        assertEquals(result, HttpStatus.OK);
    }
}
