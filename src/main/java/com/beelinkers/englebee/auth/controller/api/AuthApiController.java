package com.beelinkers.englebee.auth.controller.api;

import static com.beelinkers.englebee.auth.constant.AuthConstant.SESSION_MEMBER_KEY;
import static com.beelinkers.englebee.auth.constant.AuthConstant.SIGNUP_PROGRESS_SESSION_MEMBER_KEY;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.auth.dto.request.StudentSignupRequestDTO;
import com.beelinkers.englebee.auth.exception.SignupProgressSessionNotFoundException;
import com.beelinkers.englebee.auth.oauth2.session.SessionMember;
import com.beelinkers.englebee.auth.oauth2.session.SignupProgressSessionMember;
import com.beelinkers.englebee.auth.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthApiController {

  private final AuthService authService;

  @PostMapping("/signup/student")
  public ResponseEntity<Void> signupStudent(HttpSession httpSession,
      @RequestBody @Valid StudentSignupRequestDTO studentSignupRequestDTO) {
    SignupProgressSessionMember signupProgressSessionMember = (SignupProgressSessionMember) httpSession.getAttribute(
        SIGNUP_PROGRESS_SESSION_MEMBER_KEY);
    if (signupProgressSessionMember == null) {
      throw new SignupProgressSessionNotFoundException("잘못된 회원가입 요청입니다.");
    }
    Member member = authService.signupStudent(signupProgressSessionMember, studentSignupRequestDTO);
    httpSession.removeAttribute(SIGNUP_PROGRESS_SESSION_MEMBER_KEY);
    httpSession.setAttribute(SESSION_MEMBER_KEY,
        new SessionMember(member.getSeq(), member.getRole()));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }


}
