package com.beelinkers.englebee.auth.oauth2.session;

import com.beelinkers.englebee.auth.domain.entity.LoginType;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupProgressSessionMember implements Serializable {

  private String email;

  private LoginType loginType;
}
