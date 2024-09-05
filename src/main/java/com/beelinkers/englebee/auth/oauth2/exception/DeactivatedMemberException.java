package com.beelinkers.englebee.auth.oauth2.exception;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

public class DeactivatedMemberException extends OAuth2AuthenticationException {

  public DeactivatedMemberException(String msg) {
    super(msg);
  }
}
