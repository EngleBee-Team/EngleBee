package com.beelinkers.englebee.general.service.impl;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.auth.domain.repository.MemberRepository;
import com.beelinkers.englebee.general.exception.MemberNicknameDuplicatedException;
import com.beelinkers.englebee.general.exception.MemberNotFoundException;
import com.beelinkers.englebee.general.service.GeneralMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class GeneralMemberServiceImpl implements GeneralMemberService {

  private final MemberRepository memberRepository;

  @Override
  @Transactional(readOnly = true)
  public void checkNicknameDuplicated(String nickname) {
    boolean present = memberRepository.findByNickname(nickname).isPresent();
    if (present) {
      throw new MemberNicknameDuplicatedException("중복된 닉네임입니다.");
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Member findMember(Long memberSeq) {
    return memberRepository.findById(memberSeq).orElseThrow(
        () -> new MemberNotFoundException("해당하는 멤버를 찾을 수 없습니다.")
    );
  }
}
