package com.beelinkers.englebee.auth.oauth2.exception;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

public class AlreadySignedUpEmailException extends OAuth2AuthenticationException {

  public AlreadySignedUpEmailException(String msg) {
    super(msg);
  }
}
