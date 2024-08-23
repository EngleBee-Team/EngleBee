package com.beelinkers.englebee.general.domain.entity;

import com.beelinkers.englebee.general.exception.InvalidSubjectCodeException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubjectCode {
  GRAMMAR("어법"),
  SENTENCE("문장"),
  WORD("단어");

  private final String koreanCode;

  public static SubjectCode fromKoreanCode(String koreanCode) {
    for (SubjectCode subject : values()) {
      if (subject.getKoreanCode().equals(koreanCode)) {
        return subject;
      }
    }
    throw new InvalidSubjectCodeException("유효하지 않은 과목 입력입니다: " + koreanCode);
  }
}
