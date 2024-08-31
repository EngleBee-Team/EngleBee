package com.beelinkers.englebee.general.exception.handler;

import com.beelinkers.englebee.general.exception.ExamNotFoundException;
import com.beelinkers.englebee.general.exception.InvalidExamStatusException;
import com.beelinkers.englebee.general.exception.InvalidMemberRoleException;
import com.beelinkers.englebee.general.exception.MemberNicknameDuplicatedException;
import com.beelinkers.englebee.general.exception.MemberNicknameLengthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
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

  @ExceptionHandler(ExamNotFoundException.class)
  public ResponseEntity<String> handleExamNotFoundException(
      ExamNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidMemberRoleException.class)
  public ResponseEntity<String> handleInvalidMemberRoleException(
      InvalidMemberRoleException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidExamStatusException.class)
  public ResponseEntity<String> handleInvalidExamStatusException(
      InvalidExamStatusException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

}
