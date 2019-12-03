package com.example.postsapi.exception;

public class PostNotFoundException extends Exception {

    public PostNotFoundException (String msg) {
        super(msg);
    }
}
