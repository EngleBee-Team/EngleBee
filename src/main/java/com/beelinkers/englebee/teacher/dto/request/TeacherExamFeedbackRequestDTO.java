package com.beelinkers.englebee.teacher.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherExamFeedbackRequestDTO {

  @Length(max = 500)
  private String feedback;
  private List<TeacherEvaluateStudentSubjectLevelDTO> teacherEvaluateStudentSubjectLevelDTOs;
}
