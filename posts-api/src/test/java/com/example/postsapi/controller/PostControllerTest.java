package com.example.postsapi.controller;

import com.example.postsapi.model.Post;
import com.example.postsapi.service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

    @Test
    public void createPost_Post_Success() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .header("username", "username")
                .content(createPostInJson("title", "description", "username"));


        when(postService.createPost((any()), any())).thenReturn(post);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"title\":\"title\",\"description\":\"description\",\"user\":{\"username\":\"username\"}}"))
                .andReturn();

    }

    @Test
    public void getPostList_ListOfPosts_Success() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/list")
                .contentType(MediaType.APPLICATION_JSON);


        when(postService.getAll()).thenReturn((Iterable<Post>) post);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
//                .andExpect(content().json("{\"postId\":1,\"title\":\"title\",\"body\":\"body\",\"author\":{\"userId\":1,\"username\":\"name3\",\"password\":\"pass\",\"email\":\"name@domain.com\",\"address\":null,\"mobile\":null,\"addlEmail\":null},\"comments\":null}"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }
}
