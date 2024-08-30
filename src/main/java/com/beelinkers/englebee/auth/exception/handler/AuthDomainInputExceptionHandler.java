package com.beelinkers.englebee.auth.exception.handler;

import com.beelinkers.englebee.auth.exception.SignupProgressSessionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthDomainInputExceptionHandler {

  @ExceptionHandler(SignupProgressSessionNotFoundException.class)
  public ResponseEntity<String> handleSignupProgressSessionNotFoundException(
      SignupProgressSessionNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
