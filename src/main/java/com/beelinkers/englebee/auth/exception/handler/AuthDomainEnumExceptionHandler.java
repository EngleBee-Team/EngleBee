package com.beelinkers.englebee.auth.exception.handler;

import com.beelinkers.englebee.auth.exception.InvalidLoginTypeException;
import com.beelinkers.englebee.auth.exception.InvalidStudentGradeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthDomainEnumExceptionHandler {

  @ExceptionHandler(InvalidStudentGradeException.class)
  public ResponseEntity<String> handleInvalidStudentGradeException(
      InvalidStudentGradeException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidLoginTypeException.class)
  public ResponseEntity<String> handleInvalidLoginTypeException(
      InvalidLoginTypeException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }
}