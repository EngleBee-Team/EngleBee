package com.beelinkers.englebee.teacher.controller.api;

import com.beelinkers.englebee.general.dto.response.GeneralPagedResponseDTO;
import com.beelinkers.englebee.general.dto.response.PaginationResponseDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageAuthoredExamDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageLectureDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageNewExamDTO;
import com.beelinkers.englebee.teacher.service.TeacherMainService;
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
@RequestMapping("/api/teacher/main")
public class TeacherMainApiController {

  private final TeacherMainService teacherMainService;

  @GetMapping("/lecture")
  public ResponseEntity<GeneralPagedResponseDTO<TeacherMainPageLectureDTO>> getLectureList(
      @RequestParam("memberSeq") Long memberSeq, @PageableDefault(size = 10) Pageable pageable
  ) {
    Page<TeacherMainPageLectureDTO> lectureList = teacherMainService.getLectureList(memberSeq,
        pageable);
    PaginationResponseDTO pagination = new PaginationResponseDTO(
        lectureList.getNumber() + 1, lectureList.getSize(), lectureList.getTotalPages(),
        lectureList.getTotalElements(), lectureList.hasPrevious(), lectureList.hasNext()
    );
    GeneralPagedResponseDTO<TeacherMainPageLectureDTO> pagedPagination = new GeneralPagedResponseDTO<>(
        lectureList.getContent(),
        pagination
    );
    return ResponseEntity.ok(pagedPagination);
  }

  @GetMapping("/new-exam")
  public ResponseEntity<GeneralPagedResponseDTO<TeacherMainPageNewExamDTO>> getNewExamList(
      @RequestParam("memberSeq") Long memberSeq, @PageableDefault(size = 10) Pageable pageable
  ) {
    Page<TeacherMainPageNewExamDTO> newExamList = teacherMainService.getNewExamList(memberSeq,
        pageable);
    PaginationResponseDTO pagination = new PaginationResponseDTO(
        newExamList.getNumber() + 1, newExamList.getSize(), newExamList.getTotalPages(),
        newExamList.getTotalElements(), newExamList.hasPrevious(), newExamList.hasNext()
    );
    GeneralPagedResponseDTO<TeacherMainPageNewExamDTO> pagedPagination = new GeneralPagedResponseDTO<>(
        newExamList.getContent(),
        pagination
    );
    return ResponseEntity.ok(pagedPagination);
  }

  @GetMapping("/authored-exam")
  public ResponseEntity<GeneralPagedResponseDTO<TeacherMainPageAuthoredExamDTO>> getAuthoredExamList(
      @RequestParam("memberSeq") Long memberSeq, @PageableDefault(size = 10) Pageable pageable
  ) {
    Page<TeacherMainPageAuthoredExamDTO> authoredExamList = teacherMainService.getAuthoredExamList(
        memberSeq, pageable);
    PaginationResponseDTO pagination = new PaginationResponseDTO(
        authoredExamList.getNumber() + 1, authoredExamList.getSize(),
        authoredExamList.getTotalPages(),
        authoredExamList.getTotalElements(), authoredExamList.hasPrevious(),
        authoredExamList.hasNext()
    );
    GeneralPagedResponseDTO<TeacherMainPageAuthoredExamDTO> pagedPagination = new GeneralPagedResponseDTO<>(
        authoredExamList.getContent(),
        pagination
    );
    return ResponseEntity.ok(pagedPagination);
  }
}
