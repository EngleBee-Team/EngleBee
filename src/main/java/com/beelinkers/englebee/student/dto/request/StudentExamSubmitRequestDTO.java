package com.beelinkers.englebee.student.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentExamSubmitRequestDTO {

  List<StudentAnswerToTeacherQuestionDTO> studentAnswerToTeacherQuestionDTOs;

}
