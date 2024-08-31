package com.beelinkers.englebee.teacher.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherExamRegisterRequestDTO {

  private List<TeacherQuestionCreateRequestDTO> teacherQuestionCreateRequestDTOs;
}
