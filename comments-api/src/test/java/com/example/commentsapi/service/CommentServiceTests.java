package com.example.commentsapi.service;

import com.example.commentsapi.feign.PostsClient;
import com.example.commentsapi.model.Comment;
import com.example.commentsapi.repository.CommentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTests {

    @InjectMocks
    private CommentServiceImpl commentService;

    @InjectMocks
    private Comment comment;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostsClient postsClient;

    @Before
    public void initializeDummyComment() {
        comment.setId(1L);
        comment.setText("text");
        comment.setUsername("username");
        comment.setPostId(1L);
    }

    @Test
    public void createComment_Comment_Success() throws Exception {
        Comment commentRequest = new Comment();
        commentRequest.setText("text");

        when(commentRepository.save(any())).thenReturn(comment);
        when(postsClient.postWithPostIdExists(anyLong())).thenReturn(true);

        Comment result = commentService.createComment(commentRequest, 1L,"username");

        assertNotNull(result);
        assertEquals(result, comment);
    }

    @Test
    public void deleteCommentsByPostId_HttpStatusOK_Success() {
        HttpStatus result = commentService.deleteCommentsByPostId(1L);

        assertNotNull(result);
        assertEquals(result, HttpStatus.OK);
    }

    @Test
    public void deleteComment_HttpStatusOK_Success() {
        HttpStatus result = commentService.deleteComment(1L);

        assertNotNull(result);
        assertEquals(result, HttpStatus.OK);
    }

    @Test
    public void searchById_Comment_Success() {
        when(commentRepository.findById(anyLong())).thenReturn(java.util.Optional.of(comment));
        Comment result = commentService.searchById(1L);

        assertNotNull(result);
        assertEquals(result, comment);
    }

    @Test
    public void getCommentsByPostId_ListOfComments_Success() {
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);

        when(commentRepository.findAllByPostId(anyLong())).thenReturn(comments);

        Iterable<Comment> result = commentService.getCommentsByPostId(1L);

        assertNotNull(result);
        assertEquals(result, comments);
    }

}
