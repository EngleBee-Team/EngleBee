package com.beelinkers.englebee.auth.controller.api;

import static com.beelinkers.englebee.auth.constant.AuthConstant.SESSION_MEMBER_KEY;
import static com.beelinkers.englebee.auth.constant.AuthConstant.SIGNUP_PROGRESS_SESSION_MEMBER_KEY;

import com.beelinkers.englebee.auth.annotation.SignupProgressMember;
import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.auth.dto.request.NicknameCheckRequestDTO;
import com.beelinkers.englebee.auth.dto.request.StudentSignupRequestDTO;
import com.beelinkers.englebee.auth.dto.request.TeacherSignupRequestDTO;
import com.beelinkers.englebee.auth.exception.SignupProgressSessionNotFoundException;
import com.beelinkers.englebee.auth.oauth2.session.SessionMember;
import com.beelinkers.englebee.auth.oauth2.session.SignupProgressSessionMember;
import com.beelinkers.englebee.auth.service.AuthService;
import com.beelinkers.englebee.general.service.GeneralMemberService;
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
  private final GeneralMemberService generalMemberService;

  @PostMapping("/nickname-duplicated")
  public ResponseEntity<Void> checkNicknameIsDuplicated(
      @SignupProgressMember SignupProgressSessionMember signupProgressSessionMember,
      @RequestBody @Valid NicknameCheckRequestDTO nicknameCheckRequestDTO) {
    log.info("하이 닉네임 " + signupProgressSessionMember.getEmail());
    log.info("하이 로그인타입 " + signupProgressSessionMember.getLoginType());
    checkSignupProgressMemberSessionExist(signupProgressSessionMember);
    generalMemberService.checkNicknameDuplicated(nicknameCheckRequestDTO.getNickname());
    return ResponseEntity.ok().build();
  }

  @PostMapping("/signup/student")
  public ResponseEntity<Void> signupStudent(
      @SignupProgressMember SignupProgressSessionMember signupProgressSessionMember,
      @RequestBody @Valid StudentSignupRequestDTO studentSignupRequestDTO,
      HttpSession httpSession) {
    log.info("하이 회원가입 닉네임 " + signupProgressSessionMember.getEmail());
    log.info("하이 회원가입 로그인타입 " + signupProgressSessionMember.getLoginType());
    checkSignupProgressMemberSessionExist(signupProgressSessionMember);
    Member member = authService.signupStudent(signupProgressSessionMember, studentSignupRequestDTO);
    makeMemberSession(httpSession, member);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/signup/teacher")
  public ResponseEntity<Void> signupTeacher(
      @SignupProgressMember SignupProgressSessionMember signupProgressSessionMember,
      @RequestBody @Valid TeacherSignupRequestDTO teacherSignupRequestDTO,
      HttpSession httpSession) {
    checkSignupProgressMemberSessionExist(signupProgressSessionMember);
    Member member = authService.signupTeacher(signupProgressSessionMember, teacherSignupRequestDTO);
    makeMemberSession(httpSession, member);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  private void checkSignupProgressMemberSessionExist(
      SignupProgressSessionMember signupProgressSessionMember) {
    if (signupProgressSessionMember == null) {
      throw new SignupProgressSessionNotFoundException("잘못된 회원가입 요청입니다.");
    }
  }

  private void makeMemberSession(HttpSession httpSession, Member member) {
    httpSession.removeAttribute(SIGNUP_PROGRESS_SESSION_MEMBER_KEY);
    httpSession.setAttribute(SESSION_MEMBER_KEY,
        new SessionMember(member.getSeq(), member.getRole()));
  }

}
