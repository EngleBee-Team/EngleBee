package com.beelinkers.englebee.teacher.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherMyPageWrittenReplyDTO {

  private Long seq;
  private Long questionSeq;
  private String Content;
  private LocalDateTime createdAt;
}
