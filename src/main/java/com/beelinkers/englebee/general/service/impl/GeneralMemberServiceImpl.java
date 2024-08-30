package com.beelinkers.englebee.general.service.impl;

import com.beelinkers.englebee.auth.domain.repository.MemberRepository;
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
  public Boolean checkNicknameDuplicated(String nickname) {
    return memberRepository.findByNickname(nickname).isPresent();
  }
}
