package com.example.postsapi.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExceptionHandlerTests {

    @InjectMocks
    private ExceptionHandler exceptionHandler;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Test
    public void handleException_ResponseEntity_Success() {
        Exception exception = new Exception("message");
        ResponseEntity result = exceptionHandler.handleException(exception);
        ErrorResponse resultBody = (ErrorResponse) result.getBody();
        assertEquals(exception.getMessage(), resultBody.getMessage());
    }

    @Test
    public void handlePostNotFoundException_ResponseEntity_Success() {
        PostNotFoundException exception = new PostNotFoundException("message");
        ResponseEntity result = exceptionHandler.handlePostNotFoundException(exception);
        ErrorResponse resultBody = (ErrorResponse) result.getBody();
        assertEquals(exception.getMessage(), resultBody.getMessage());
    }

    @Test
    public void handleHttpMessageNotReadable_ResponseEntity_Success() {
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException("message");
        ResponseEntity result = exceptionHandler.handleHttpMessageNotReadable(exception,null, null, null);
        ErrorResponse resultBody = (ErrorResponse) result.getBody();
        assertEquals(exception.getMessage(), resultBody.getMessage());
    }

    @Test
    public void handleMethodArgumentNotValid_ResponseEntity_Success() {
        when(methodArgumentNotValidException.getMessage()).thenReturn("[type] default message message");
        ResponseEntity result = exceptionHandler.handleMethodArgumentNotValid(methodArgumentNotValidException,null, null, null);

        ErrorResponse resultBody = (ErrorResponse) result.getBody();
        assertEquals("[type]: Message", resultBody.getMessage());
    }

    @Test
    public void parseNotValidExceptionMessage_Message_Success() {
        when(methodArgumentNotValidException.getMessage()).thenReturn("[type] default message message");
        String result = exceptionHandler.parseNotValidExceptionMessage(methodArgumentNotValidException);
        assertEquals("[type]: Message", result);
    }
}
