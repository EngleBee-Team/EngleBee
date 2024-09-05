package com.beelinkers.englebee.general.dto.response.mapper;

import com.beelinkers.englebee.general.domain.entity.Question;
import com.beelinkers.englebee.general.dto.response.QnaDetailPageResponseDTO;
import com.beelinkers.englebee.general.dto.response.ReplyResponseDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QnaDetailPageResponseMapper {

  private final ReplyResponseMapper replyResponseMapper;

  public QnaDetailPageResponseDTO qnaDetailResponseDTO(Question qna) {
    List<ReplyResponseDTO> replyResponseList = qna.getReplies().stream()
        .map(replyResponseMapper::replyResponseDTO)
        .collect(Collectors.toList());

    return new QnaDetailPageResponseDTO(
        qna.getSeq(),
        qna.getTitle(),
        qna.getContent(),
        qna.getMember().getNickname(),
        qna.getCreatedAt(),
        replyResponseList
    );
  }
}
