package com.beelinkers.englebee.teacher.service;

import com.beelinkers.englebee.teacher.dto.request.TeacherAccountPageRequestDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherAccountUpdateDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherInfoDTO;

public interface TeacherAccountService {

  TeacherInfoDTO getMemberInfo(Long memberSeq);

  TeacherAccountUpdateDTO updateTeacherInfo(Long memberSeq,
      TeacherAccountPageRequestDTO teacherAccountRequestDTO);

  void checkNicknameDuplicate(String nickname);

  void deleteTeacherAccountInfo(Long memberSeq);

}
