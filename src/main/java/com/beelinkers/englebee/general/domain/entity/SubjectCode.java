package com.beelinkers.englebee.general.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubjectCode {
  GRAMMAR("어법"),
  SENTENCE("문장"),
  WORD("단어");

  private final String koreanCode;
}
