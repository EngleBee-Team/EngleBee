package com.beelinkers.englebee.student.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentAnswerToTeacherQuestionDTO {

  private Long teacherQuestionSeq;

  @Min(1)
  @Max(4)
  private Integer studentAnswer;
}
