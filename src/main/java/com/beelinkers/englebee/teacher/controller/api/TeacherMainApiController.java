package com.beelinkers.englebee.teacher.controller.api;

import com.beelinkers.englebee.general.dto.response.GeneralPagedResponseDTO;
import com.beelinkers.englebee.general.dto.response.PaginationResponseDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageLectureDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageNewExamDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageQuestionDTO;
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
    Page<TeacherMainPageLectureDTO> lectureList = teacherMainService.getLectureList(memberSeq, pageable);
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

  @GetMapping("/question")
  public ResponseEntity<GeneralPagedResponseDTO<TeacherMainPageQuestionDTO>> getQuestionList(
          @PageableDefault(size = 10) Pageable pageable
  ) {
    Page<TeacherMainPageQuestionDTO> questionList = teacherMainService.getQuestionList(pageable);
    PaginationResponseDTO pagination = new PaginationResponseDTO(
            questionList.getNumber() + 1, questionList.getSize(), questionList.getTotalPages(),
            questionList.getTotalElements(), questionList.hasPrevious(), questionList.hasNext()
    );
    GeneralPagedResponseDTO<TeacherMainPageQuestionDTO> pagedPagination = new GeneralPagedResponseDTO<>(
            questionList.getContent(),
            pagination
    );
    return ResponseEntity.ok(pagedPagination);
  }

  @GetMapping("/new-exam")
  public ResponseEntity<GeneralPagedResponseDTO<TeacherMainPageNewExamDTO>> getNewExamList(
          @RequestParam("memberSeq") Long memberSeq, @PageableDefault(size = 10) Pageable pageable
  ) {
    Page<TeacherMainPageNewExamDTO> newExamList = teacherMainService.getNewExamList(memberSeq, pageable);
    PaginationResponseDTO pagination = new PaginationResponseDTO(
      newExamList.getNumber()+1, newExamList.getSize(), newExamList.getTotalPages(),
            newExamList.getTotalElements(), newExamList.hasPrevious(), newExamList.hasNext()
    );
    GeneralPagedResponseDTO<TeacherMainPageNewExamDTO> pagedPagination = new GeneralPagedResponseDTO<>(
      newExamList.getContent(),
      pagination
    );
    return ResponseEntity.ok(pagedPagination);
  }


}
