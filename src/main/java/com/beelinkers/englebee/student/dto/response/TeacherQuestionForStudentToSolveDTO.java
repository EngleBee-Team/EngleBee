package com.beelinkers.englebee.student.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeacherQuestionForStudentToSolveDTO {

  private String direction;
  private List<String> choices;

}
