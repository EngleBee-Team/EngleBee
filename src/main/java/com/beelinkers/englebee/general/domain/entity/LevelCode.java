package com.beelinkers.englebee.general.domain.entity;

import com.beelinkers.englebee.general.exception.InvalidLevelCodeException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LevelCode {

  LOW("하"),
  MIDDLE_LOW("중하"),
  MIDDLE("중"),
  MIDDLE_HIGH("중상"),
  HIGH("상");

  private final String koreanCode;

  public static LevelCode fromKoreanCode(String koreanCode) {
    for (LevelCode level : values()) {
      if (level.getKoreanCode().equals(koreanCode)) {
        return level;
      }
    }
    throw new InvalidLevelCodeException("유효하지 않은 레벨 입력입니다: " + koreanCode);
  }

}
