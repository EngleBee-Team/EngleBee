package com.beelinkers.englebee.auth.oauth2.exception;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

public class UnsupportedSocialLoginTypeException extends OAuth2AuthenticationException {

  public UnsupportedSocialLoginTypeException(String msg) {
    super(msg);
  }
}
