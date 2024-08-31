package com.beelinkers.englebee.teacher.controller.api;

import com.beelinkers.englebee.auth.annotation.LoginMember;
import com.beelinkers.englebee.auth.oauth2.session.SessionMember;
import com.beelinkers.englebee.teacher.dto.request.TeacherExamRegisterRequestDTO;
import com.beelinkers.englebee.teacher.service.TeacherExamService;
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
@RequestMapping("/api/teacher/exam")
public class TeacherExamApiController {

  private final TeacherExamService teacherExamService;

  @PostMapping("/register/{examSeq}")
  public ResponseEntity<Void> registerExam(@LoginMember SessionMember sessionMember,
      @PathVariable("examSeq") Long examSeq,
      @Valid @RequestBody TeacherExamRegisterRequestDTO teacherExamRegisterRequestDTO) {
    teacherExamService.registerExam(sessionMember.getSeq(), examSeq, teacherExamRegisterRequestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
