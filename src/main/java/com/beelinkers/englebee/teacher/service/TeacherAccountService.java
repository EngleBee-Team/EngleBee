package com.beelinkers.englebee.teacher.service;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.teacher.dto.request.TeacherAccountPageRequestDTO;

public interface TeacherAccountService {

  Member getMemberInfo(Long memberSeq);

  Member updateTeacherInfo(Long memberSeq, TeacherAccountPageRequestDTO teacherAccountRequestDTO);

  boolean checkNicknameDuplicate(String nickname);

  void deleteTeacherAccountInfo(Long memberSeq);

}
