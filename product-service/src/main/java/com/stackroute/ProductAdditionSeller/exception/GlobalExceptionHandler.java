package com.stackroute.ProductAdditionSeller.exception;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class GlobalExceptionHandler {
    @Autowired
    Environment environment;

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleExceptionForIdNotFound(IdNotFoundException exception) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
        errorInfo.setErrorCode("500");
        errorInfo.setTime(LocalDateTime.now());
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
