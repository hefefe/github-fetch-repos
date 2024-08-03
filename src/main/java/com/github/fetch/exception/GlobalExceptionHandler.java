package com.github.fetch.exception;

import com.github.fetch.exception.model.ErrorDto;
import com.github.fetch.exception.model.GithubErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<GithubErrorResponse> handleCustomErrorException(CustomErrorException ex) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(ErrorDto.builder()
                        .status(ex.getHttpStatus().value())
                        .message(ex.getMessage())
                        .build());
    }
}
