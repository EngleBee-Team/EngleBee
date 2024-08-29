package com.beelinkers.englebee.general.controller.api;

import com.beelinkers.englebee.auth.annotation.LoginMember;
import com.beelinkers.englebee.auth.oauth2.session.SessionMember;
import com.beelinkers.englebee.general.exception.MemberNicknameLengthException;
import com.beelinkers.englebee.general.service.GeneralMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/general/member")
public class MemberApiController {

  private final GeneralMemberService generalMemberService;

  @PostMapping("/nickname-duplicated")
  public ResponseEntity<Boolean> checkNicknameIsDuplicated(@LoginMember SessionMember sessionMember,
      @RequestParam String nickname) {
    if (nickname.length() > 20 || nickname.isEmpty()) {
      throw new MemberNicknameLengthException("닉네임의 길이는 1글자 ~ 20글자 사이여야 합니다.");
    }

    return ResponseEntity.ok()
        .body(generalMemberService.checkNicknameDuplicated(nickname));
  }


}
