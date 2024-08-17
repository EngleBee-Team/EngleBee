package com.beelinkers.englebee.admin.exception.handler;

import com.beelinkers.englebee.admin.exception.TempAdminCustomerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TempAdminExceptionHandler {

  @ExceptionHandler(TempAdminCustomerException.class)
  public ResponseEntity<String> handlerTempAdminCustomerException(TempAdminCustomerException ex) {
    return ResponseEntity.badRequest().body("잘못된 요청입니다.");
  }

}
