package com.beelinkers.englebee.general.controller.api;

import com.beelinkers.englebee.general.dto.response.GeneralPagedResponseDTO;
import com.beelinkers.englebee.general.dto.response.PaginationResponseDTO;
import com.beelinkers.englebee.general.dto.response.QnaPageResponseDTO;
import com.beelinkers.englebee.general.service.QnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/general/qna")
@RequiredArgsConstructor
public class QnaApiController {

  private final QnaService qnaService;

  @GetMapping("/list")
  public ResponseEntity<GeneralPagedResponseDTO<QnaPageResponseDTO>> getQnaList(
      @PageableDefault(size = 10) Pageable pageable
  ) {
    Page<QnaPageResponseDTO> QnaInfoList = qnaService.getQnaListInfo(pageable);
    PaginationResponseDTO pagination = new PaginationResponseDTO(
        QnaInfoList.getNumber(), QnaInfoList.getSize(), QnaInfoList.getTotalPages(),
        QnaInfoList.getTotalElements(), QnaInfoList.hasPrevious(), QnaInfoList.hasNext()
    );
    GeneralPagedResponseDTO<QnaPageResponseDTO> resultQna = new GeneralPagedResponseDTO<>(
        QnaInfoList.getContent(), pagination
    );

    return ResponseEntity.ok(resultQna);
  }
}
