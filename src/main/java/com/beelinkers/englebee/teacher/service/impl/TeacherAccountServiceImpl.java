package com.beelinkers.englebee.teacher.service.impl;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.auth.domain.repository.MemberRepository;
import com.beelinkers.englebee.general.exception.MemberNotFoundException;
import com.beelinkers.englebee.general.service.GeneralMemberService;
import com.beelinkers.englebee.teacher.dto.request.TeacherAccountPageRequestDTO;
import com.beelinkers.englebee.teacher.service.TeacherAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TeacherAccountServiceImpl implements TeacherAccountService {

  private final GeneralMemberService generalMemberService;
  private final MemberRepository memberRepository;

  @Override
  @Transactional(readOnly = true)
  public Member getMemberInfo(Long memberSeq) {
    return memberRepository.findById(memberSeq)
        .orElseThrow(() -> new MemberNotFoundException("해당 회원을 찾을 수 없습니다."));
  }

  @Override
  @Transactional
  public boolean checkNicknameDuplicate(String nickname) {
    return generalMemberService.checkNicknameDuplicated(nickname);
  }

  @Override
  @Transactional
  public Member updateTeacherInfo(Long memberSeq,
      TeacherAccountPageRequestDTO teacherAccountRequestDTO) {
    Member member = memberRepository.findById(memberSeq)
        .orElseThrow(() -> new RuntimeException("수정에 실패하였습니다."));
    member.updateNickname(teacherAccountRequestDTO.getNickname());
    return memberRepository.save(member);
  }

  @Override
  @Transactional
  public void deleteTeacherAccountInfo(Long memberSeq) {
    Member member = memberRepository.findById(memberSeq)
        .orElseThrow(() -> new MemberNotFoundException("해당 유저가 존재하지 않습니다."));
    if (!member.isDeactivated()) {
      member.deactivate();
    }
  }

}
