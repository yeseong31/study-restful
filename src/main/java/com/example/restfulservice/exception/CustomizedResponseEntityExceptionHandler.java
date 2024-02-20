package com.example.restfulservice.exception;

import jakarta.annotation.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.createBuilder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(exceptionResponse, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.createBuilder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(exceptionResponse, NOT_FOUND);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public final ResponseEntity<Object> handlePostNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.createBuilder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(exceptionResponse, NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @Nullable HttpHeaders headers,
                                                                  @Nullable HttpStatusCode status,
                                                                  @Nullable WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.createBuilder()
                .timestamp(LocalDateTime.now())
                .message("Validation failed")
                .details(ex.getBindingResult().toString())
                .build();

        return new ResponseEntity<>(exceptionResponse, BAD_REQUEST);
    }
}
