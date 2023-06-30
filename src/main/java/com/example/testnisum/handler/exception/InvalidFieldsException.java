package com.example.testnisum.handler.exception;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

public class InvalidFieldsException extends RuntimeException {
    public InvalidFieldsException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(toString(constraintViolations));
    }

    private static String toString(Set<? extends ConstraintViolation<?>> constraintViolations) {
        return (String)constraintViolations.stream().map((cv) -> {
            return cv == null ? "null" : cv.getPropertyPath() + ": " + cv.getMessage();
        }).collect(Collectors.joining(", "));
    }
}
