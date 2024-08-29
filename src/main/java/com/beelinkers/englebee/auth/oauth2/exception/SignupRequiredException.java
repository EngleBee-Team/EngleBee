package com.beelinkers.englebee.auth.oauth2.exception;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

public class SignupRequiredException extends OAuth2AuthenticationException {

  public SignupRequiredException(String msg) {
    super(msg);
  }
}
