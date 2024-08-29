package com.beelinkers.englebee.auth.service;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.auth.dto.request.StudentSignupRequestDTO;
import com.beelinkers.englebee.auth.dto.request.TeacherSignupRequestDTO;
import com.beelinkers.englebee.auth.oauth2.session.SignupProgressSessionMember;

public interface AuthService {

  Member signupStudent(SignupProgressSessionMember signupProgressSessionMember,
      StudentSignupRequestDTO studentSignupRequestDTO);

  Member signupTeacher(SignupProgressSessionMember signupProgressSessionMember,
      TeacherSignupRequestDTO teacherSignupRequestDTO);

  void deactivateAccount(Long memberSeq);
}
