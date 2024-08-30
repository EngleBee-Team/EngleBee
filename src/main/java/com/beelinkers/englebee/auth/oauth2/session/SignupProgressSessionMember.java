package com.beelinkers.englebee.auth.oauth2.session;

import com.beelinkers.englebee.auth.domain.entity.LoginType;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupProgressSessionMember implements Serializable {

  private final String email;

  private final LoginType loginType;
}
