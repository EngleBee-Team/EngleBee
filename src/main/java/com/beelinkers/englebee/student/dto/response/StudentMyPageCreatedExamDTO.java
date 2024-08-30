package com.beelinkers.englebee.student.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentMyPageCreatedExamDTO {

  private Long seq;
  private Long lectureSeq;
  private String title;
  private String status;
  private String teacherNickname;
  private LocalDateTime createdAt;
}
