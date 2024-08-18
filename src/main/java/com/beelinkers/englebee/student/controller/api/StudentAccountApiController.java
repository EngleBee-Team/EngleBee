package com.beelinkers.englebee.student.controller.api;

import com.beelinkers.englebee.student.service.StudentAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student/account")
public class StudentAccountApiController {

  private final StudentAccountService studentAccountService;
  /*
     TODO : 공통 계정 기능은 /general 폴더에 넣고
       학생 전용 계정 기능 ( 학년 수정 )은 여기서 다룸.
   */
}
