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

  @Length(max = 1500, message = "피드백은 1500글자까지 작성할 수 있습니다.")
  private String feedback;
  private List<TeacherEvaluateStudentSubjectLevelDTO> teacherEvaluateStudentSubjectLevelDTOs;
}
