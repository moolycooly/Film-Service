package org.fintech.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseStatusException handleException(Exception e) {
        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Произошло внутреняя ошибка сервера: " + e.getMessage());
    }
}
