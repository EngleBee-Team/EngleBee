package com.beelinkers.englebee.student.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentAccountPageUpdateRequestDTO {

  @NotNull(message = "닉네임 입력이 필요합니다.")
  @Length(min = 1, max = 20, message = "닉네임 길이는 1글자 ~ 20글자 사이여야 합니다.")
  private String nickname;

  @NotNull(message = "학생 멤버의 학년 값이 필요합니다.")
  @NotBlank(message = "학생 멤버의 학년 값이 비어있으면 안됩니다.")
  private String grade;


  public void updateStudentInfo(String nickname, String grade) {
    this.nickname = nickname;
    this.grade = grade;
  }

}
