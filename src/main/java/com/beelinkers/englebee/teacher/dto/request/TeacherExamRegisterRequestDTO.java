package com.beelinkers.englebee.teacher.dto.request;

import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherExamRegisterRequestDTO {

  @Length(min = 1, max = 50, message = "시험 제목은 1글자 ~ 50글자 사이여야 합니다.")
  private String title;
  @Valid
  private List<TeacherQuestionCreateRequestDTO> teacherQuestionCreateRequestDTOs;
}
