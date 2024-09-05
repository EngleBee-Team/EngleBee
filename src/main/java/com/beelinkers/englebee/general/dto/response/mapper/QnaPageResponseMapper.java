package com.beelinkers.englebee.general.dto.response.mapper;

import com.beelinkers.englebee.general.domain.entity.Question;
import com.beelinkers.englebee.general.dto.response.QnaPageResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class QnaPageResponseMapper {

  public QnaPageResponseDTO qnaPageResponseDTO(Question question) {
    return new QnaPageResponseDTO(
        question.getSeq(),
        question.getTitle(),
        question.getMember().getNickname(),
        question.getCreatedAt()
    );
  }

}
