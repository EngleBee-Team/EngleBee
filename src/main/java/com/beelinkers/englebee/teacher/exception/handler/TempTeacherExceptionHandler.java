package com.beelinkers.englebee.teacher.exception.handler;

import com.beelinkers.englebee.teacher.exception.TempTeacherCustomerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TempTeacherExceptionHandler {

  @ExceptionHandler(TempTeacherCustomerException.class)
  public ResponseEntity<String> handlerTempAdminCustomerException(TempTeacherCustomerException ex) {
    return ResponseEntity.badRequest().body("잘못된 요청입니다.");
  }

}
