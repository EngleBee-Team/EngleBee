package com.beelinkers.englebee.teacher.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherWrittenQnaDTO {

  private Long seq;
  private String title;
  private String content;
  private LocalDateTime createdAt;
}
