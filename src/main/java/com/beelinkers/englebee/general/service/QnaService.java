package com.beelinkers.englebee.general.service;

import com.beelinkers.englebee.general.dto.response.QnaPageResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QnaService {

  Page<QnaPageResponseDTO> getQnaListInfo(Pageable pageable);
}
