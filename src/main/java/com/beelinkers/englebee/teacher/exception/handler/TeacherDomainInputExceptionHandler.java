package com.beelinkers.englebee.teacher.exception.handler;

import com.beelinkers.englebee.teacher.exception.TeacherIllegalAccessToExamException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TeacherDomainInputExceptionHandler {

  @ExceptionHandler(TeacherIllegalAccessToExamException.class)
  public ResponseEntity<String> handleTeacherIllegalAccessToExamException(
      TeacherIllegalAccessToExamException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
