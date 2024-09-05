package com.beelinkers.englebee.general.dto.response.mapper;

import com.beelinkers.englebee.general.domain.entity.Reply;
import com.beelinkers.englebee.general.dto.response.ReplyResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ReplyResponseMapper {

  public ReplyResponseDTO replyResponseDTO(Reply reply) {
    return new ReplyResponseDTO(
        reply.getSeq(),
        reply.getContent(),
        reply.getMember().getNickname(),
        reply.getCreatedAt()
    );
  }

}
