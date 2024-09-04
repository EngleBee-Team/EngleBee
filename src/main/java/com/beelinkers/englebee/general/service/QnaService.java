package com.beelinkers.englebee.general.service;

import com.beelinkers.englebee.general.domain.entity.Question;
import com.beelinkers.englebee.general.dto.request.QnaPageRequestDTO;
import com.beelinkers.englebee.general.dto.response.QnaPageResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QnaService {

  Question registerQuestion(QnaPageRequestDTO qnaRequestDTO, Long memberSeq);

  Page<QnaPageResponseDTO> getQnaListInfo(Pageable pageable);
}
