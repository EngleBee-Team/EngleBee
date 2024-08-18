package com.beelinkers.englebee.general.exception.handler;

import com.beelinkers.englebee.general.exception.TempGeneralCustomerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TempGeneralExceptionHandler {

  @ExceptionHandler(TempGeneralCustomerException.class)
  public ResponseEntity<String> handlerTempAdminCustomerException(TempGeneralCustomerException ex) {
    return ResponseEntity.badRequest().body("잘못된 요청입니다.");
  }

}
