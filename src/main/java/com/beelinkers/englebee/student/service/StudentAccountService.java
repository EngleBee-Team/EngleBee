package com.beelinkers.englebee.student.service;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.student.dto.request.StudentAccountPageRequestDTO;

public interface StudentAccountService {

  Member updateStudentInfo(Long memberSeq,
      StudentAccountPageRequestDTO studentAccountPageUpdateRequestDTO);

  void deleteStudentAccountInfo(Long memberSeq);

  void checkNicknameDuplicated(String nickname);

  Member getMemberInfo(Long memberSeq);
}
