package com.beelinkers.englebee.general.controller.api;

import com.beelinkers.englebee.general.service.GeneralMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/general/member")
public class MemberApiController {

  private final GeneralMemberService generalMemberService;
  /*
    TODO : 유저 공통 기능 ( ex. 닉네임 수정 시 중복 체크 확인 기능과 같은 공통 기능 구현 )
   */
}
