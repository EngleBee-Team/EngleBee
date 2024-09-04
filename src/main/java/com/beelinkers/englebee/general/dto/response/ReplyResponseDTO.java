package com.beelinkers.englebee.general.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyResponseDTO {

  private Long seq;
  private String content;
  private String authorName;
  private LocalDateTime createdAt;
  
}
