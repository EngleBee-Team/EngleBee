package com.beelinkers.englebee.student.controller.api;

import com.beelinkers.englebee.auth.annotation.LoginMember;
import com.beelinkers.englebee.auth.oauth2.session.SessionMember;
import com.beelinkers.englebee.student.dto.request.StudentExamSolveRequestDTO;
import com.beelinkers.englebee.student.service.StudentExamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student/exam")
public class StudentExamApiController {

  private final StudentExamService studentExamService;

  @PostMapping("/solve/{examSeq}")
  public ResponseEntity<Void> solveExam(@LoginMember SessionMember sessionMember,
      @PathVariable("examSeq") Long examSeq,
      @Valid @RequestBody StudentExamSolveRequestDTO studentExamSolveRequestDTO) {
    studentExamService.solveExam(sessionMember.getSeq(), examSeq, studentExamSolveRequestDTO);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
