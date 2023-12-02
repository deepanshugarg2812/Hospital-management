package com.auth.layer.AuthenticationLib.controller;


import com.auth.layer.AuthenticationLib.dto.response.ResponseWrapper;
import com.auth.layer.AuthenticationLib.exception.ApplicationRuntimeException;
import lombok.extern.slf4j.Slf4j;
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
