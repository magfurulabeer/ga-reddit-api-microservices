package com.example.usersapi.exception;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ErrorResponseTest {

    @InjectMocks
    ErrorResponse response;

    @Before
    public void init() {
        response.setCause("cause");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        response.setMessage("message");
        response.setTimestamp("timestamp");
    }

    @Test
    public void getHttpStatus_HttpStatus_Success() {
        HttpStatus status = response.getHttpStatus();
        assertEquals(status, HttpStatus.NOT_FOUND);
    }

    @Test
    public void getMessage_String_Success() {
        String message = response.getMessage();
        assertEquals(message, "message");
    }

    @Test
    public void getTimeStamp_String_Success() {
        String timestamp = response.getTimestamp();
        assertEquals(timestamp, "timestamp");
    }

    @Test
    public void getCause_String_Success() {
        String cause = response.getCause();
        assertEquals(cause, "cause");
    }

    @Test
    public void constructor_ErrorResponse_Success() {
        ErrorResponse errResponse = new ErrorResponse(HttpStatus.I_AM_A_TEAPOT, "Short and Stout");
        assertEquals(errResponse.getHttpStatus(), HttpStatus.I_AM_A_TEAPOT);
        assertEquals(errResponse.getMessage(), "Short and Stout");
    }
}
