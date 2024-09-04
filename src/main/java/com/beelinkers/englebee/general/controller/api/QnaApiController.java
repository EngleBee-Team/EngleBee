package com.beelinkers.englebee.general.controller.api;

import com.beelinkers.englebee.general.dto.request.QnaPageRequestDTO;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping("/register")
  public ResponseEntity<String> registerQuestion(@RequestBody QnaPageRequestDTO qnaRequestDTO) {
    if (qnaRequestDTO.getTitle() == null || qnaRequestDTO.getTitle().isEmpty()) {
      return ResponseEntity.badRequest().body("제목을 입력해주세요.");
    } else if (qnaRequestDTO.getContent() == null || qnaRequestDTO.getContent().isEmpty()) {
      return ResponseEntity.badRequest().body("내용을 입력해주세요.");
    }
    try {
      Long memberSeq = qnaRequestDTO.getMemberSeq();
      qnaService.registerQnaInfo(qnaRequestDTO, memberSeq);
      return ResponseEntity.ok("게시물이 등록되었습니다.");
    } catch (Exception e) {
      log.error("게시글 등록 중 오류 발생 : {} ", e.getMessage());
      return ResponseEntity.badRequest().body("게시글 등록 중 오류가 발생했습니다.");
    }
  }
}
