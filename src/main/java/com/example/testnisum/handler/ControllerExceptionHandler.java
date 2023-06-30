package com.example.testnisum.handler;

import com.example.testnisum.handler.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class ControllerExceptionHandler implements AuthenticationEntryPoint {

    @ExceptionHandler(AlreadyRegisteredUserException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage alreadyRegisteredUserException(AlreadyRegisteredUserException ex) {
        ErrorMessage message = new ErrorMessage(ex.getMessage());
        return message;
    }

    @ExceptionHandler(InvalidEmailException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage invalidEmailException(InvalidEmailException ex) {
        ErrorMessage message = new ErrorMessage(ex.getMessage());
        return message;
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage invalidPasswordException(InvalidPasswordException ex) {
        ErrorMessage message = new ErrorMessage(ex.getMessage());
        return message;
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage userNotFoundException(UserNotFoundException ex) {
        ErrorMessage message = new ErrorMessage(ex.getMessage());
        return message;
    }

    @ExceptionHandler(InvalidFieldsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage onConstraintValidationException(InvalidFieldsException e) {
        ErrorMessage message = new ErrorMessage(e.getMessage());
        return message;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AccessDeniedException accessDeniedException) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         Exception exception) throws IOException {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}