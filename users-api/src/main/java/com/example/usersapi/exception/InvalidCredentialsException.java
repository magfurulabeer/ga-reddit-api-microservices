package com.example.usersapi.exception;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException (String msg) {
        super(msg);
    }
}