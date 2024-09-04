package com.beelinkers.englebee.teacher.service.impl;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.auth.domain.repository.MemberRepository;
import com.beelinkers.englebee.general.exception.MemberNotFoundException;
import com.beelinkers.englebee.general.service.GeneralMemberService;
import com.beelinkers.englebee.teacher.dto.request.TeacherAccountPageRequestDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherAccountUpdateDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherInfoDTO;
import com.beelinkers.englebee.teacher.dto.response.mapper.TeacherInfoMapper;
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
  private final TeacherInfoMapper teacherInfoMapper;
  private final MemberRepository memberRepository;

  @Override
  @Transactional(readOnly = true)
  public TeacherInfoDTO getMemberInfo(Long memberSeq) {
    Member member = memberRepository.findById(memberSeq)
        .orElseThrow(() -> new MemberNotFoundException("회원 정보를 찾을 수 없습니다."));
    return teacherInfoMapper.teacherInfo(member);
  }

  @Override
  @Transactional(readOnly = true)
  public void checkNicknameDuplicate(String nickname) {
    generalMemberService.checkNicknameDuplicated(nickname);
  }

  @Override
  @Transactional
  public TeacherAccountUpdateDTO updateTeacherInfo(Long memberSeq,
      TeacherAccountPageRequestDTO teacherAccountRequestDTO) {
    Member member = memberRepository.findById(memberSeq)
        .orElseThrow(() -> new RuntimeException("수정에 실패하였습니다."));
    member.updateNickname(teacherAccountRequestDTO.getNickname());
    return new TeacherAccountUpdateDTO(memberSeq, teacherAccountRequestDTO.getNickname());
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
