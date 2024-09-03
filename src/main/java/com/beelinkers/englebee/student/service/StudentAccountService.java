package com.beelinkers.englebee.student.service;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.student.dto.request.StudentAccountPageUpdateRequestDTO;

public interface StudentAccountService {

  Member updateStudentInfo(Long memberSeq,
      StudentAccountPageUpdateRequestDTO studentAccountPageUpdateRequestDTO);

  void deleteStudentAccountInfo(Long memberSeq);

  boolean checkNicknameDuplicated(String nickname);

  Member getMemberInfo(Long memberSeq);
}
