package com.beelinkers.englebee.general.service.impl;

import com.beelinkers.englebee.general.domain.repository.QuestionRepository;
import com.beelinkers.englebee.general.dto.response.QnaPageResponseDTO;
import com.beelinkers.englebee.general.dto.response.mapper.QnaPageResponseMapper;
import com.beelinkers.englebee.general.service.QnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class QnaServiceImpl implements QnaService {

  private final QuestionRepository questionRepository;
  private final QnaPageResponseMapper qnaPageMapper;

  @Override
  @Transactional(readOnly = true)
  public Page<QnaPageResponseDTO> getQnaListInfo(Pageable pageable) {
    return questionRepository.findAllByOrderByCreatedAtDesc(pageable)
        .map(qnaPageMapper::qnaPageResponseDTO);
  }

}
