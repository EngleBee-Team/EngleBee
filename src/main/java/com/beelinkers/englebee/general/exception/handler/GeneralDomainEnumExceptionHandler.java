package com.beelinkers.englebee.general.exception.handler;

import com.beelinkers.englebee.general.exception.InvalidLevelCodeException;
import com.beelinkers.englebee.general.exception.InvalidSubjectCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralDomainEnumExceptionHandler {

  @ExceptionHandler(InvalidLevelCodeException.class)
  public ResponseEntity<String> handleInvalidLevelCodeException(InvalidLevelCodeException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidSubjectCodeException.class)
  public ResponseEntity<String> handleInvalidSubjectCodeException(InvalidSubjectCodeException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
