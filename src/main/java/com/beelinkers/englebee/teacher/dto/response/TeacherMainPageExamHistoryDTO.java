package com.beelinkers.englebee.teacher.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherMainPageExamHistoryDTO {

  private Long seq;
  private Long lectureSeq;
  private String title;
  private String status;
  private String studentNickname;
  private LocalDateTime createdAt;
}
