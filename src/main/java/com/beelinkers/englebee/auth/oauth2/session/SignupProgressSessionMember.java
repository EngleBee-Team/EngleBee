package com.beelinkers.englebee.auth.oauth2.session;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class SignupProgressSessionMember implements Serializable {

  private final String email;
}
