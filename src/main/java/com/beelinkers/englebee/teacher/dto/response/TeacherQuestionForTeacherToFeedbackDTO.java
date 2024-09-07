package com.beelinkers.englebee.teacher.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeacherQuestionForTeacherToFeedbackDTO {

  private Long teacherQuestionSeq;
  private String direction;
  private List<String> choices;
  private Integer correctAnswer;
  private Integer studentAnswer;
  private String intent;

}
