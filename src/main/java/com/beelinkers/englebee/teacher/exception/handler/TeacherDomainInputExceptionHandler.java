package com.beelinkers.englebee.teacher.exception.handler;

import com.beelinkers.englebee.teacher.exception.SubjectLevelNotFoundException;
import com.beelinkers.englebee.teacher.exception.TeacherIllegalAccessToExamException;
import com.beelinkers.englebee.teacher.exception.TeacherQuestionNotFoundException;
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

  @ExceptionHandler(TeacherQuestionNotFoundException.class)
  public ResponseEntity<String> handleTeacherQuestionNotFoundException(
      TeacherQuestionNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(SubjectLevelNotFoundException.class)
  public ResponseEntity<String> handleSubjectLevelNotFoundException(
      SubjectLevelNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
