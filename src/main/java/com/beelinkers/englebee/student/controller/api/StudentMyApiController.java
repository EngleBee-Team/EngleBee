package com.beelinkers.englebee.student.controller.api;

import com.beelinkers.englebee.general.dto.response.GeneralPagedResponseDTO;
import com.beelinkers.englebee.general.dto.response.PaginationResponseDTO;
import com.beelinkers.englebee.student.dto.response.StudentMyPageCompletedExamDTO;
import com.beelinkers.englebee.student.dto.response.StudentMyPageCreatedExamDTO;
import com.beelinkers.englebee.student.dto.response.StudentMyPageWrittenQnaDTO;
import com.beelinkers.englebee.student.dto.response.StudentMyPageWrittenReplyDTO;
import com.beelinkers.englebee.student.service.StudentMyService;
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
@RequestMapping("/api/student/my")
public class StudentMyApiController {

  private final StudentMyService studentMyService;

  @GetMapping("/created-exam")
  public ResponseEntity<GeneralPagedResponseDTO<StudentMyPageCreatedExamDTO>> getStudentMyCreatedExam(
      @RequestParam("memberSeq") Long memberSeq,
      @PageableDefault(size = 10) Pageable pageable
  ) {
    Page<StudentMyPageCreatedExamDTO> createdExamList = studentMyService.getStudentMyCreatedExamInfo(
        memberSeq, pageable);
    PaginationResponseDTO pagination = new PaginationResponseDTO(
        createdExamList.getNumber(), createdExamList.getSize(), createdExamList.getTotalPages(),
        createdExamList.getTotalPages(), createdExamList.hasPrevious(), createdExamList.hasNext()
    );
    GeneralPagedResponseDTO<StudentMyPageCreatedExamDTO> createdExamPagination = new GeneralPagedResponseDTO<>(
        createdExamList.getContent(), pagination
    );

    return ResponseEntity.ok(createdExamPagination);
  }

  @GetMapping("/completed-exam")
  public ResponseEntity<GeneralPagedResponseDTO<StudentMyPageCompletedExamDTO>> getStudentMyCompletedExam(
      @RequestParam("memberSeq") Long memberSeq,
      @PageableDefault(size = 10) Pageable pageable
  ) {
    Page<StudentMyPageCompletedExamDTO> completedExamList = studentMyService.getStudentMyCompletedExamInfo(
        memberSeq, pageable);
    PaginationResponseDTO pagination = new PaginationResponseDTO(
        completedExamList.getNumber(), completedExamList.getSize(),
        completedExamList.getTotalPages(),
        completedExamList.getTotalElements(), completedExamList.hasPrevious(),
        completedExamList.hasNext()
    );
    GeneralPagedResponseDTO<StudentMyPageCompletedExamDTO> completedExamPagination = new GeneralPagedResponseDTO<>(
        completedExamList.getContent(), pagination
    );

    return ResponseEntity.ok(completedExamPagination);
  }

  @GetMapping("/written-qna")
  public ResponseEntity<GeneralPagedResponseDTO<StudentMyPageWrittenQnaDTO>> getStudentMyWrittenQna(
      @RequestParam("memberSeq") Long memberSeq,
      @PageableDefault(size = 10) Pageable pageable
  ) {
    Page<StudentMyPageWrittenQnaDTO> writtenQnaList = studentMyService.getStudentMyWrittenQnaInfo(
        memberSeq, pageable
    );
    PaginationResponseDTO pagination = new PaginationResponseDTO(
        writtenQnaList.getNumber(), writtenQnaList.getSize(), writtenQnaList.getTotalPages(),
        writtenQnaList.getTotalElements(), writtenQnaList.hasPrevious(), writtenQnaList.hasNext()
    );
    GeneralPagedResponseDTO<StudentMyPageWrittenQnaDTO> writtenQnaPagination = new GeneralPagedResponseDTO<>(
        writtenQnaList.getContent(), pagination
    );
    return ResponseEntity.ok(writtenQnaPagination);
  }

  @GetMapping("/written-reply")
  public ResponseEntity<GeneralPagedResponseDTO<StudentMyPageWrittenReplyDTO>> getStudentMyWrittenReply(
      @RequestParam("memberSeq") Long memberSeq, @PageableDefault(size = 10) Pageable pageable
  ) {
    Page<StudentMyPageWrittenReplyDTO> writtenReplyList = studentMyService.getStudentMyWrittenReplyInfo(
        memberSeq, pageable
    );
    PaginationResponseDTO pagination = new PaginationResponseDTO(
        writtenReplyList.getNumber(), writtenReplyList.getSize(), writtenReplyList.getTotalPages(),
        writtenReplyList.getTotalElements(), writtenReplyList.hasPrevious(),
        writtenReplyList.hasNext()
    );
    GeneralPagedResponseDTO<StudentMyPageWrittenReplyDTO> writtenReplyPagination = new GeneralPagedResponseDTO<>(
        writtenReplyList.getContent(), pagination
    );
    return ResponseEntity.ok(writtenReplyPagination);
  }

}
