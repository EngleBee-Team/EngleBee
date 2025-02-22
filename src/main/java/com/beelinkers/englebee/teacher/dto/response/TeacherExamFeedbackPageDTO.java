package com.beelinkers.englebee.teacher.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeacherExamFeedbackPageDTO {

  private String studentGrade;
  private String examTitle;
  private List<TeacherQuestionForTeacherToFeedbackDTO> teacherQuestionForTeacherToFeedbackDTOs;
  private List<String> lectureSubjects;

}
