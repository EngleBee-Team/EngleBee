package com.beelinkers.englebee.general.service;

import com.beelinkers.englebee.auth.domain.entity.Member;

public interface GeneralMemberService {

  void checkNicknameDuplicated(String nickname);

  Member findMember(Long memberSeq);

}
