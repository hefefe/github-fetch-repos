package com.github.fetch.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class CustomErrorException extends RuntimeException {

    private final String message;

    private final HttpStatus httpStatus;
}
