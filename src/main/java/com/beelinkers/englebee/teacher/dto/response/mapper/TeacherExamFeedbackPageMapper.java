package com.beelinkers.englebee.teacher.dto.response.mapper;

import com.beelinkers.englebee.teacher.domain.entity.TeacherQuestion;
import com.beelinkers.englebee.teacher.dto.response.TeacherExamFeedbackPageDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherQuestionForTeacherToFeedbackDTO;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TeacherExamFeedbackPageMapper {

  public TeacherExamFeedbackPageDTO toExamFeedbackPageDTO(String examTitle,
      List<TeacherQuestion> teacherQuestions) {
    List<TeacherQuestionForTeacherToFeedbackDTO> teacherQuestionForStudentToSolveDTOs = teacherQuestions.stream()
        .map(tq -> new TeacherQuestionForTeacherToFeedbackDTO(
            tq.getDirection(),
            Arrays.stream(tq.getChoices().split(","))
                .map(String::trim)
                .toList(),
            tq.getCorrectAnswer(),
            tq.getStudentAnswer(),
            tq.getIntent()))
        .toList();
    return new TeacherExamFeedbackPageDTO(examTitle, teacherQuestionForStudentToSolveDTOs);
  }
}
