package com.beelinkers.englebee.auth.domain.entity;

import com.beelinkers.englebee.auth.exception.InvalidStudentGradeException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoginType {
  GOOGLE("google"),
  NAVER("naver");

  private final String alias;

  public static LoginType fromAlias(String alias) {
    for (LoginType loginType : values()) {
      if (loginType.getAlias().equals(alias)) {
        return loginType;
      }
    }
    throw new InvalidStudentGradeException("유효하지 않은 로그인 타입 입력입니다: " + alias);
  }

  public boolean isGoogle() {
    return this == GOOGLE;
  }

  public boolean isNaver() {
    return this == NAVER;
  }

}
