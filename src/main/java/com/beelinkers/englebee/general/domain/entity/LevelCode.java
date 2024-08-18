package com.beelinkers.englebee.general.domain.entity;

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
  
}
