package com.beelinkers.englebee.general.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QnaPageRequestDTO {

  private Long memberSeq;
  private String title;
  private String content;

}
