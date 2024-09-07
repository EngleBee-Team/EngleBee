package com.beelinkers.englebee.student.exception.handler;

import com.beelinkers.englebee.student.exception.StudentIllegalAccessToExamException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentDomainInputExceptionHandler {

  @ExceptionHandler(StudentIllegalAccessToExamException.class)
  public ResponseEntity<String> handlerStudentIllegalAccessToExamException(
      StudentIllegalAccessToExamException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

}
