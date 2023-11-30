package com.user.management.controller;

import com.user.management.dto.response.ResponseWrapper;
import com.user.management.exception.ApplicationRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception controller
 */
@Slf4j
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(ApplicationRuntimeException.class)
    public ResponseWrapper<String> applicationRunTime(ApplicationRuntimeException e) {
        log.error("Error occured while processing request ", e);
        return new ResponseWrapper<>(e.getStatusCode(), e.getMessage());
    }
}
