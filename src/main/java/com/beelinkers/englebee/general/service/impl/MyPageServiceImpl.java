package com.beelinkers.englebee.general.service.impl;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.auth.domain.repository.MemberRepository;
import com.beelinkers.englebee.general.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

  private final MemberRepository memberRepository;

  @Override
  public String getNickname(Long memberSeq) {
    Member member = memberRepository.findById(memberSeq)
        .orElseThrow(() -> new IllegalArgumentException("닉네임을 불러올 수 없습니다."));
    return member.getNickname();
  }
}
