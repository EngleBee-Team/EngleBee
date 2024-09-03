package com.beelinkers.englebee.student.service.impl;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.auth.domain.entity.StudentGrade;
import com.beelinkers.englebee.auth.domain.repository.MemberRepository;
import com.beelinkers.englebee.general.exception.MemberNotFoundException;
import com.beelinkers.englebee.general.service.GeneralMemberService;
import com.beelinkers.englebee.student.dto.request.StudentAccountPageRequestDTO;
import com.beelinkers.englebee.student.service.StudentAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class StudentAccountServiceImpl implements StudentAccountService {

  private final GeneralMemberService generalMemberService;
  private final MemberRepository memberRepository;

  // 회원 정보
  @Override
  @Transactional(readOnly = true)
  public Member getMemberInfo(Long memberSeq) {
    return memberRepository.findById(memberSeq)
        .orElseThrow(() -> new MemberNotFoundException("해당 회원을 찾을 수 없습니다."));
  }

  // 닉네임 중복 검사
  @Override
  @Transactional
  public boolean checkNicknameDuplicated(String nickname) {
    return generalMemberService.checkNicknameDuplicated(nickname);
  }

  // 회원 정보 수정
  @Override
  @Transactional
  public Member updateStudentInfo(Long memberSeq,
      StudentAccountPageRequestDTO updateStudentRequest) {
    Member member = memberRepository.findById(memberSeq)
        .orElseThrow(() -> new RuntimeException("수정에 실패 하였습니다."));

    member.updateNickname(updateStudentRequest.getNickname());
    member.updateStudentGrade(StudentGrade.fromKoreanGrade(updateStudentRequest.getGrade()));

    return memberRepository.save(member);
  }

  // 회원 탈퇴
  @Override
  @Transactional
  public void deleteStudentAccountInfo(Long memberSeq) {
    Member member = memberRepository.findById(memberSeq)
        .orElseThrow(() -> new MemberNotFoundException("해당 유저가 존재하지 않습니다."));
    if (!member.isDeactivated()) {
      member.deactivate();
    }
  }

  ;
}
