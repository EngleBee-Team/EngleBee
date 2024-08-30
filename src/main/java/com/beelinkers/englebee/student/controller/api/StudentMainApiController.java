package com.beelinkers.englebee.student.controller.api;

import com.beelinkers.englebee.general.dto.response.GeneralPagedResponseDTO;
import com.beelinkers.englebee.general.dto.response.PaginationResponseDTO;
import com.beelinkers.englebee.student.dto.response.*;
import com.beelinkers.englebee.student.service.StudentMainService;
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
        Page<StudentMainPageLectureDTO> lectureList = studentMainService.getLectureList(memberSeq, pageable);
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

    @GetMapping("/question")
    public ResponseEntity<GeneralPagedResponseDTO<StudentMainPageQuestionDTO>> getQnaList(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<StudentMainPageQuestionDTO> questionList = studentMainService.getQuestionList(pageable);
        PaginationResponseDTO pagination = new PaginationResponseDTO(
                questionList.getNumber(), questionList.getSize(), questionList.getTotalPages(),
                questionList.getTotalElements(), questionList.hasPrevious(), questionList.hasNext()
        );
        GeneralPagedResponseDTO<StudentMainPageQuestionDTO> pagedPagination = new GeneralPagedResponseDTO<>(
                questionList.getContent(),
                pagination
        );
        return ResponseEntity.ok(pagedPagination);
    }

    @GetMapping("/new-exams")
    public ResponseEntity<GeneralPagedResponseDTO<StudentMainPageNewExamDTO>> getCreatedExam(
            @RequestParam("memberSeq") Long memberSeq,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<StudentMainPageNewExamDTO> newExamList = studentMainService.getNewExamList(memberSeq, pageable);
        PaginationResponseDTO pagination = new PaginationResponseDTO(
                newExamList.getNumber(), newExamList.getSize(), newExamList.getTotalPages(),
                newExamList.getTotalElements(), newExamList.hasPrevious(), newExamList.hasNext()
        );
        GeneralPagedResponseDTO<StudentMainPageNewExamDTO> pagedPagination = new GeneralPagedResponseDTO<>(
                newExamList.getContent(),
                pagination
        );
        return ResponseEntity.ok(pagedPagination);
    }

    @GetMapping("/submit-exams")
    public ResponseEntity<GeneralPagedResponseDTO<StudentMainPageSubmitExamDTO>> getCompletedExam(
            @RequestParam("memberSeq") Long memberSeq,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<StudentMainPageSubmitExamDTO> submitExamList = studentMainService.getSubmitExamList(memberSeq, pageable);
        PaginationResponseDTO pagination = new PaginationResponseDTO(
                submitExamList.getNumber(), submitExamList.getSize(), submitExamList.getTotalPages(),
                submitExamList.getTotalElements(), submitExamList.hasPrevious(), submitExamList.hasNext()
        );
        GeneralPagedResponseDTO<StudentMainPageSubmitExamDTO> pagedPagination = new GeneralPagedResponseDTO<>(
                submitExamList.getContent(),
                pagination
        );
        return ResponseEntity.ok(pagedPagination);
    }


}
