package com.beelinkers.englebee.student.controller.api;

import com.beelinkers.englebee.student.service.StudentMyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student/my")
public class StudentMyApiController {

  private final StudentMyService studentMyService;
}
