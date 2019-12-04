package com.example.commentsapi.controller;

import com.example.commentsapi.exception.PostNotFoundException;
import com.example.commentsapi.model.Comment;
import com.example.commentsapi.service.CommentService;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class CommentControllerTests {
    private MockMvc mockMvc;

    @InjectMocks
    private CommentController commentController;

    @InjectMocks
    private Comment comment;

    @Mock
    private CommentService commentService;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }

    @Before
    public void initializeDummyComment() {
        comment.setId(1L);
        comment.setText("text");
        comment.setUsername("username");
        comment.setPostId(1L);
    }

    private static String createCommentInJson(String text, long postId, String username) {
        return "{ \"text\": \"" + text + "\", " +
                "\"postId\": \"" + postId + "\", " +
                "\"username\":\"" + username + "\"}";
    }

    @Test
    public void createComment_Comment_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("username", "username")
                .content(createCommentInJson("text", 1L, "username"));

        when(commentService.createComment((any()), anyLong(), anyString())).thenReturn(comment);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"postId\":1,\"text\":\"text\",\"user\":{\"username\":\"username\"}}"))
                .andReturn();
    }

    @Test
    public void getCommentsByPostId_ListOfComments_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/1");

        List<Comment> comments = new ArrayList<>();
        comments.add(comment);

        when(commentService.getCommentsByPostId(anyLong())).thenReturn(comments);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"postId\":1,\"text\":\"text\",\"user\":{\"username\":\"username\"}}]"))
                .andReturn();
    }

    @Test
    public void deleteComment_StatusOK_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/1");

        when(commentService.deleteComment(anyLong())).thenReturn(HttpStatus.OK);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("success"))
                .andReturn();
    }

    @Test
    public void searchById_Comment_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/view/1");

        when(commentService.searchById(anyLong())).thenReturn(comment);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"postId\":1,\"text\":\"text\",\"user\":{\"username\":\"username\"}}"))
                .andReturn();
    }

    @Test
    public void deleteCommentsByPostId_HttpStatusOK_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/posts/1");

        when(commentService.deleteCommentsByPostId(anyLong())).thenReturn(HttpStatus.OK);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("\"OK\""))
                .andReturn();
    }
}
