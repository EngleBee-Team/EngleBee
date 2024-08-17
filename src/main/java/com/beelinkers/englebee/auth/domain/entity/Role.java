package com.beelinkers.englebee.auth.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

  ADMIN("ROLE_ADMIN"),
  STUDENT("ROLE_STUDENT"),
  TEACHER("ROLE_TEACHER");

  private final String key;

  public boolean isAdmin() {
    return this == ADMIN;
  }

  public boolean isStudent() {
    return this == STUDENT;
  }

  public boolean isTeacher() {
    return this == TEACHER;
  }
}
