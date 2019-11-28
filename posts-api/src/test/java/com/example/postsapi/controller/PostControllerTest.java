package com.example.postsapi.controller;

import com.example.postsapi.model.Post;
import com.example.postsapi.service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    PostController postController;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Before
    public void initializeDummyPost() {
        post.setId(1L);
        post.setTitle("title");
        post.setDescription("description");
        post.setUsername("username");
    }

    @InjectMocks
    private Post post;

    @Mock
    private PostService postService;

    private static String createPostInJson(String title, String description, String username) {
        return "{ \"title\": \"" + title + "\", " +
                "\"username\":\"" + username + "\", " +
                "\"body\":\"" + description + "\"}";
    }

    private ResultActions createPostHelper() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .header("username", "username")
                .content(createPostInJson("title", "description", "username"));

        when(postService.createPost((any()), any())).thenReturn(post);

        return mockMvc.perform(requestBuilder);
    }

    @Test
    public void createPost_Post_Success() throws Exception {
        ResultActions result = createPostHelper();

        result.andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"title\":\"title\",\"description\":\"description\",\"user\":{\"username\":\"username\"}}"))
                .andReturn();

    }

    @Test
    public void getPostList_ListOfPosts_Success() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/list")
                .contentType(MediaType.APPLICATION_JSON);

        List<Post> posts = new ArrayList<>();
        posts.add(post);

        when(postService.getAll()).thenReturn(posts);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"title\":\"title\",\"description\":\"description\",\"user\":{\"username\":\"username\"}}]"))
                .andReturn();
    }

    @Test
    public void deletePost_Post_Success() throws Exception {

        createPostHelper();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/1")
                .contentType(MediaType.APPLICATION_JSON);

        when(postService.deletePost(anyLong())).thenReturn(HttpStatus.OK);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void postWithPostIdExists_False_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/1")
                .contentType(MediaType.APPLICATION_JSON);

        when(postService.searchById(anyLong())).thenReturn(null);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("false"))
                .andReturn();
    }

    @Test
    public void postWithPostIdExists_True_Success() throws Exception {
        ResultActions action = createPostHelper();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/1")
                .contentType(MediaType.APPLICATION_JSON);

        when(postService.searchById(anyLong())).thenReturn(post);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("true"))
                .andReturn();
    }
}
