package com.beelinkers.englebee.student.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentMyPageWrittenReplyDTO {

  private Long seq;
  private Long questionSeq;
  private String Content;
  private LocalDateTime createdAt;
}
