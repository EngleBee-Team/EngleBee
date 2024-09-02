package com.beelinkers.englebee.general.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExamDetailPageDTO {

  private String examTitle;
  private List<TeacherQuestionDetailDTO> teacherQuestionDetailDTOs;
  private String feedback;
}
