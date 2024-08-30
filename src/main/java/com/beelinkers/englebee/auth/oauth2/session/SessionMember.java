package com.beelinkers.englebee.auth.oauth2.session;


import com.beelinkers.englebee.auth.domain.entity.Role;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SessionMember implements Serializable {

  private final Long seq;
  private final Role role;
}
