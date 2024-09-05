package com.beelinkers.englebee.general.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyRequestDTO {

  private Long memberSeq;
  private Long questionSeq;
  private String content;

}
