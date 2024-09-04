package com.beelinkers.englebee.general.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QnaDetailPageResponseDTO {

  private Long questionSeq;
  private String title;
  private String content;
  private String authorName;
  private LocalDateTime createdAt;
  private List<ReplyResponseDTO> replies;

}
