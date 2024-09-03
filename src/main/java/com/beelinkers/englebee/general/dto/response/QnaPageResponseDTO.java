package com.beelinkers.englebee.general.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QnaPageResponseDTO {

  private Long seq;
  private String memberNickname;
  private String title;
  private LocalDateTime createdAt;
  
}
