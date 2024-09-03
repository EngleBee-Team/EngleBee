package com.beelinkers.englebee.teacher.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherAccountPageRequestDTO {

  @NotNull(message = "닉네임 입력이 필요합니다.")
  @Length(min = 1, max = 20, message = "닉네임 길이는 1글자 ~ 20글자 사이여야 합니다.")
  private String nickname;

  public void updateTeacherInfo(String nickname) {
    this.nickname = nickname;
  }
}
