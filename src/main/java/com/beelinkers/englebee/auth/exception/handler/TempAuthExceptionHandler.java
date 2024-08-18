package com.beelinkers.englebee.auth.exception.handler;

import com.beelinkers.englebee.auth.exception.TempAuthCustomerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TempAuthExceptionHandler {

  @ExceptionHandler(TempAuthCustomerException.class)
  public ResponseEntity<String> handlerTempAdminCustomerException(TempAuthCustomerException ex) {
    return ResponseEntity.badRequest().body("잘못된 요청입니다.");
  }

}
