package com.example.testnisum.handler.exception;
public class AlreadyRegisteredUserException extends RuntimeException {

    public AlreadyRegisteredUserException(String message) {
        super(message);
    }
}
