package com.beelinkers.englebee.student.controller.api;

import com.beelinkers.englebee.general.domain.entity.ExamStatus;
import com.beelinkers.englebee.general.dto.response.GeneralPagedResponseDTO;
import com.beelinkers.englebee.general.dto.response.PaginationResponseDTO;
import com.beelinkers.englebee.student.dto.response.StudentMainPageLectureDTO;
import com.beelinkers.englebee.student.dto.response.StudentMainPageNewExamDTO;
import com.beelinkers.englebee.student.dto.response.StudentMainPageSubmitExamDTO;
import com.beelinkers.englebee.student.service.StudentMainService;
import java.util.List;
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
@RequestMapping("/api/student/main")
public class StudentMainApiController {

  private final StudentMainService studentMainService;

  @GetMapping("/lecture")
  public ResponseEntity<GeneralPagedResponseDTO<StudentMainPageLectureDTO>> getOngoingLecture(
      @RequestParam("memberSeq") Long memberSeq,
      @PageableDefault(size = 10) Pageable pageable) {
    Page<StudentMainPageLectureDTO> lectureList = studentMainService.getLectureList(memberSeq,
        pageable);
    PaginationResponseDTO pagination = new PaginationResponseDTO(
        lectureList.getNumber(), lectureList.getSize(), lectureList.getTotalPages(),
        lectureList.getTotalElements(), lectureList.hasPrevious(), lectureList.hasNext()
    );
    GeneralPagedResponseDTO<StudentMainPageLectureDTO> pagedPagination = new GeneralPagedResponseDTO<>(
        lectureList.getContent(),
        pagination
    );
    return ResponseEntity.ok(pagedPagination);
  }

  @GetMapping("/created-exam")
  public ResponseEntity<GeneralPagedResponseDTO<StudentMainPageNewExamDTO>> getCreatedExam(
      @RequestParam("memberSeq") Long memberSeq,
      @RequestParam(value = "status", required = false, defaultValue = "PREPARED") ExamStatus status) {
    List<StudentMainPageNewExamDTO> createdExamList = studentMainService.getNewExamList(memberSeq,
        status);
    GeneralPagedResponseDTO<StudentMainPageNewExamDTO> examPagination = new GeneralPagedResponseDTO<>(
        createdExamList,
        new PaginationResponseDTO(1, createdExamList.size(), 1, createdExamList.size(), false,
            false)
    );
    return ResponseEntity.ok(examPagination);
  }

  @GetMapping("/completed-exam")
  public ResponseEntity<GeneralPagedResponseDTO<StudentMainPageSubmitExamDTO>> getCompletedExam(
      @RequestParam("memberSeq") Long memberSeq,
      @RequestParam(value = "status", required = false) List<ExamStatus> status) {
    if (status == null || status.isEmpty()) {
      status = List.of(ExamStatus.SUBMITTED, ExamStatus.FEEDBACK_COMPLETED);
    }
    List<StudentMainPageSubmitExamDTO> completedExamList = studentMainService.getSubmitExamList(
        memberSeq, status);
    GeneralPagedResponseDTO<StudentMainPageSubmitExamDTO> examPagination = new GeneralPagedResponseDTO<>(
        completedExamList,
        new PaginationResponseDTO(1, completedExamList.size(), 1, completedExamList.size(), false,
            false)
    );
    return ResponseEntity.ok(examPagination);
  }


}
