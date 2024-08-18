package com.beelinkers.englebee.student.exception.handler;

import com.beelinkers.englebee.student.exception.TempStudentCustomerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TempStudentExceptionHandler {

  @ExceptionHandler(TempStudentCustomerException.class)
  public ResponseEntity<String> handlerTempAdminCustomerException(TempStudentCustomerException ex) {
    return ResponseEntity.badRequest().body("잘못된 요청입니다.");
  }

}
