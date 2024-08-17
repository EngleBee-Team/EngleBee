package com.beelinkers.englebee.auth.domain.entity;

import lombok.Getter;

@Getter
public enum MemberStatus {

  NORMAL,
  DEACTIVATED;

  public boolean isActive() {
    return this == NORMAL;
  }

  public boolean isDeActivated() {
    return this == DEACTIVATED;
  }
}
