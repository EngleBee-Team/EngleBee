package com.beelinkers.englebee.general.service;

import com.beelinkers.englebee.general.dto.request.QnaPageRequestDTO;
import com.beelinkers.englebee.general.dto.request.ReplyRequestDTO;
import com.beelinkers.englebee.general.dto.response.QnaDetailPageResponseDTO;
import com.beelinkers.englebee.general.dto.response.QnaPageResponseDTO;
import com.beelinkers.englebee.general.dto.response.ReplyResponseDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QnaService {

  void registerQnaInfo(QnaPageRequestDTO qnaRequestDTO, Long memberSeq);

  Page<QnaPageResponseDTO> getQnaListInfo(Pageable pageable);

  QnaDetailPageResponseDTO getQnaDetailInfo(Long questionSeq);

  ReplyResponseDTO registerReplyInfo(ReplyRequestDTO replyRequestDTO);

  List<ReplyResponseDTO> getReplyListInfo(Long questionSeq);

}
