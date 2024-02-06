package com.example.restfulservice.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class ExceptionResponse {

    private LocalDateTime timestamp;
    private String message;
    private String details;

    private ExceptionResponse(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    @Builder(builderMethodName = "createBuilder")
    public static ExceptionResponse of(LocalDateTime timestamp, String message, String details) {
        return new ExceptionResponse(timestamp, message, details);
    }
}
