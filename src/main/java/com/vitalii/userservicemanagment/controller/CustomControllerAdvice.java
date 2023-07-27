package com.vitalii.userservicemanagment.controller;

import com.vitalii.userservicemanagment.exception.CustomAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice {
    @ExceptionHandler(CustomAuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(CustomAuthenticationException ex) {
        System.out.println("Handling AuthenticationException");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
