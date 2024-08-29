package com.beelinkers.englebee.auth.service.impl;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.auth.domain.entity.Role;
import com.beelinkers.englebee.auth.domain.entity.StudentGrade;
import com.beelinkers.englebee.auth.domain.repository.MemberRepository;
import com.beelinkers.englebee.auth.dto.request.StudentSignupRequestDTO;
import com.beelinkers.englebee.auth.oauth2.session.SignupProgressSessionMember;
import com.beelinkers.englebee.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final MemberRepository memberRepository;

  @Override
  public Member signupStudent(SignupProgressSessionMember signupProgressSessionMember,
      StudentSignupRequestDTO studentSignupRequestDTO) {
    Member student = Member.builder()
        .email(signupProgressSessionMember.getEmail())
        .role(Role.STUDENT)
        .loginType(signupProgressSessionMember.getLoginType())
        .nickname(studentSignupRequestDTO.getNickname())
        .grade(StudentGrade.fromKoreanGrade(studentSignupRequestDTO.getGrade()))
        .personalInfoCollectionAgreed(studentSignupRequestDTO.getPersonalInfoCollectionAgreed())
        .build();
    return memberRepository.save(student);
  }
}
