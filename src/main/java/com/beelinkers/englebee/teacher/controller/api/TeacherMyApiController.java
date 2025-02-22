package com.beelinkers.englebee.teacher.controller.api;

import com.beelinkers.englebee.general.dto.response.GeneralPagedResponseDTO;
import com.beelinkers.englebee.general.dto.response.PaginationResponseDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMyPageExamHistoryDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMyPagePendingExamDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMyPageWrittenQnaDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMyPageWrittenReplyDTO;
import com.beelinkers.englebee.teacher.service.TeacherMyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher/my")
public class TeacherMyApiController {

  private final TeacherMyService teacherMyService;

  @GetMapping("/pending-exam")
  public ResponseEntity<GeneralPagedResponseDTO<TeacherMyPagePendingExamDTO>> getTeacherPendingExam(
      @RequestParam("memberSeq") Long memberSeq, @PageableDefault(size = 10) Pageable pageable
  ) {
    Page<TeacherMyPagePendingExamDTO> pendingExamList = teacherMyService.getTeacherMyPendingExamInfo(
        memberSeq, pageable
    );
    PaginationResponseDTO pagination = new PaginationResponseDTO(
        pendingExamList.getNumber(), pendingExamList.getSize(), pendingExamList.getTotalPages(),
        pendingExamList.getTotalElements(), pendingExamList.hasPrevious(), pendingExamList.hasNext()
    );
    GeneralPagedResponseDTO<TeacherMyPagePendingExamDTO> pendingExamPagination = new GeneralPagedResponseDTO<>(
        pendingExamList.getContent(), pagination
    );
    return ResponseEntity.ok(pendingExamPagination);
  }

  @GetMapping("/exam-history")
  public ResponseEntity<GeneralPagedResponseDTO<TeacherMyPageExamHistoryDTO>> getTeacherExamHistory(
      @RequestParam("memberSeq") Long memberSeq, @PageableDefault(size = 10) Pageable pageable
  ) {
    Page<TeacherMyPageExamHistoryDTO> examHistoryList = teacherMyService.getTeacherMyPageExamHistoryInfo(
        memberSeq, pageable
    );
    PaginationResponseDTO pagination = new PaginationResponseDTO(
        examHistoryList.getNumber(), examHistoryList.getSize(), examHistoryList.getTotalPages(),
        examHistoryList.getTotalElements(), examHistoryList.hasPrevious(), examHistoryList.hasNext()
    );
    GeneralPagedResponseDTO<TeacherMyPageExamHistoryDTO> examHistoryPagination = new GeneralPagedResponseDTO<>(
        examHistoryList.getContent(), pagination
    );
    return ResponseEntity.ok(examHistoryPagination);
  }

  @GetMapping("/qna-list")
  public ResponseEntity<GeneralPagedResponseDTO<TeacherMyPageWrittenQnaDTO>> getTeacherWrittenQna(
      @RequestParam("memberSeq") Long memberSeq, @PageableDefault(size = 10) Pageable pageable
  ) {
    Page<TeacherMyPageWrittenQnaDTO> questionList = teacherMyService.getTeacherMyPageWrittenQnaInfo(
        memberSeq, pageable
    );
    PaginationResponseDTO pagination = new PaginationResponseDTO(
        questionList.getNumber(), questionList.getSize(), questionList.getTotalPages(),
        questionList.getTotalElements(), questionList.hasPrevious(), questionList.hasNext()
    );
    GeneralPagedResponseDTO<TeacherMyPageWrittenQnaDTO> questionListPagination = new GeneralPagedResponseDTO<>(
        questionList.getContent(), pagination
    );
    return ResponseEntity.ok(questionListPagination);
  }

  @GetMapping("/reply-list")
  public ResponseEntity<GeneralPagedResponseDTO<TeacherMyPageWrittenReplyDTO>> getTeacherMyWrittenReply(
      @RequestParam("memberSeq") Long memberSeq, @PageableDefault(size = 10) Pageable pageable
  ) {
    Page<TeacherMyPageWrittenReplyDTO> writtenReplyList = teacherMyService.getTeacherMyPageWrittenReplyInfo(
        memberSeq, pageable
    );
    PaginationResponseDTO pagination = new PaginationResponseDTO(
        writtenReplyList.getNumber(), writtenReplyList.getSize(), writtenReplyList.getTotalPages(),
        writtenReplyList.getTotalElements(), writtenReplyList.hasPrevious(),
        writtenReplyList.hasNext()
    );
    GeneralPagedResponseDTO<TeacherMyPageWrittenReplyDTO> writtenReplyPagination = new GeneralPagedResponseDTO<>(
        writtenReplyList.getContent(), pagination
    );
    return ResponseEntity.ok(writtenReplyPagination);
  }

}

