package com.beelinkers.englebee.general.controller.api;

import com.beelinkers.englebee.general.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/general/account")
public class AccountApiController {

  private final AccountService accountService;
  /*
    TODO : 학생, 선생 유저 계정의 공통된 계정 정보 관련 기능을 다룸.
   */

}
