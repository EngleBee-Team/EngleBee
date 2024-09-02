package com.beelinkers.englebee.general.dto.response.mapper;

import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.dto.response.ExamDetailPageDTO;
import com.beelinkers.englebee.general.dto.response.TeacherQuestionDetailDTO;
import com.beelinkers.englebee.teacher.domain.entity.TeacherQuestion;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ExamDetailPageMapper {

  public ExamDetailPageDTO toExamDetailPageDTO(Exam exam, List<TeacherQuestion> teacherQuestions) {
    return new ExamDetailPageDTO(
        exam.getTitle(),
        toTeacherQuestionDetailDTOs(teacherQuestions),
        exam.getFeedback()
    );
  }

  private List<TeacherQuestionDetailDTO> toTeacherQuestionDetailDTOs(
      List<TeacherQuestion> teacherQuestions) {
    return teacherQuestions.stream()
        .map(this::toTeacherQuestionDetailDTO)
        .toList();
  }

  private TeacherQuestionDetailDTO toTeacherQuestionDetailDTO(TeacherQuestion question) {
    return new TeacherQuestionDetailDTO(
        question.getDirection(),
        Arrays.stream(question.getChoices().split(","))
            .map(String::trim)
            .toList(),
        question.getCorrectAnswer(),
        question.getStudentAnswer(),
        question.getIntent()
    );
  }

}
