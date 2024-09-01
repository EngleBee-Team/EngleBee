package com.beelinkers.englebee.teacher.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherExamRegisterRequestDTO {

  @Length(max = 50, message = "시험 제목의 최대 길이는 50자입니다.")
  private String title;
  private List<TeacherQuestionCreateRequestDTO> teacherQuestionCreateRequestDTOs;
}
