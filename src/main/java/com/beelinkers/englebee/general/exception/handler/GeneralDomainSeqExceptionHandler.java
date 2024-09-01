package com.beelinkers.englebee.general.exception.handler;

import com.beelinkers.englebee.general.exception.InvalidLectureSeqException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralDomainSeqExceptionHandler {

  @ExceptionHandler(InvalidLectureSeqException.class)
  public ResponseEntity<String> handleInvalidLectureSeqException(InvalidLectureSeqException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

}
