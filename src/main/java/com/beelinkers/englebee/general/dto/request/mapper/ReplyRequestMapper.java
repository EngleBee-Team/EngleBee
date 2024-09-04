package com.beelinkers.englebee.general.dto.request.mapper;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.general.domain.entity.Question;
import com.beelinkers.englebee.general.domain.entity.Reply;
import com.beelinkers.englebee.general.dto.request.ReplyRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class ReplyRequestMapper {

  public Reply registerReply(ReplyRequestDTO replyRequestDTO, Question qna, Member member) {
    return Reply.builder()
        .question(qna)
        .author(member)
        .content(replyRequestDTO.getContent())
        .build();
  }
}
