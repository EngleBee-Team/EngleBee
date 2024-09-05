package com.beelinkers.englebee.auth.oauth2.session;


import com.beelinkers.englebee.auth.domain.entity.Role;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SessionMember implements Serializable {

  private Long seq;
  private Role role;
}
