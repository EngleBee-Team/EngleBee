package com.beelinkers.englebee.teacher.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherEvaluateStudentSubjectLevelDTO {

  private String subjectCode;
  private String levelCode;
}
