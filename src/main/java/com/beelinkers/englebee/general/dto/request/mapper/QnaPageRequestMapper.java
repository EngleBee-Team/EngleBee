package com.beelinkers.englebee.general.dto.request.mapper;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.general.domain.entity.Question;
import com.beelinkers.englebee.general.dto.request.QnaPageRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class QnaPageRequestMapper {

  public Question registerQnaDTO(QnaPageRequestDTO qnaRequestDTO, Member member) {
    return Question.builder()
        .author(member)
        .title(qnaRequestDTO.getTitle())
        .content(qnaRequestDTO.getContent())
        .build();
  }

}
