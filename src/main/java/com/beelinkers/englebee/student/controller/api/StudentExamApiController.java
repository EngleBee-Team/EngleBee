package com.beelinkers.englebee.student.controller.api;

import com.beelinkers.englebee.student.service.StudentExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student/exam")
public class StudentExamApiController {

  private final StudentExamService studentExamService;
}
