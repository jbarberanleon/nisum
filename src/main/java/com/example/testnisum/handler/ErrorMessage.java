package com.example.testnisum.handler;

import lombok.Data;

import java.util.Date;
@Data
public class ErrorMessage {
    private String message;

    public ErrorMessage(String message) {
        this.message = message;
    }
}