package com.beelinkers.englebee.teacher.dto.request.mapper;

import com.beelinkers.englebee.teacher.domain.entity.TeacherQuestion;
import com.beelinkers.englebee.teacher.dto.request.TeacherQuestionCreateRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class TeacherQuestionRequestMapper {

  public TeacherQuestion toTeacherQuestion(TeacherQuestionCreateRequestDTO dto) {
    if (dto == null) {
      throw new IllegalArgumentException("DTO는 비어있으면 안됩니다.");
    }
    return TeacherQuestion.builder()
        .direction(dto.getDirection())
        .choices(String.join(",", dto.getChoices()))
        .correctAnswer(dto.getCorrectAnswer())
        .intent(dto.getIntent())
        .build();
  }

}
