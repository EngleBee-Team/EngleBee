package com.beelinkers.englebee.general.exception.handler;

import com.beelinkers.englebee.general.exception.MemberNicknameDuplicatedException;
import com.beelinkers.englebee.general.exception.MemberNicknameLengthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralDomainInputExceptionHandler {

  @ExceptionHandler(MemberNicknameLengthException.class)
  public ResponseEntity<String> handleNicknameLengthException(
      MemberNicknameLengthException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MemberNicknameDuplicatedException.class)
  public ResponseEntity<String> handleNicknameDuplicatedException(
      MemberNicknameDuplicatedException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

}
