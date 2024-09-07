package com.beelinkers.englebee.auth.service.impl;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.auth.domain.entity.Role;
import com.beelinkers.englebee.auth.domain.entity.StudentGrade;
import com.beelinkers.englebee.auth.domain.repository.MemberRepository;
import com.beelinkers.englebee.auth.dto.request.StudentSignupRequestDTO;
import com.beelinkers.englebee.auth.dto.request.TeacherSignupRequestDTO;
import com.beelinkers.englebee.auth.oauth2.session.SignupProgressSessionMember;
import com.beelinkers.englebee.auth.service.AuthService;
import com.beelinkers.englebee.general.domain.entity.LevelCode;
import com.beelinkers.englebee.general.domain.entity.MemberSubjectLevel;
import com.beelinkers.englebee.general.domain.entity.SubjectCode;
import com.beelinkers.englebee.general.domain.entity.SubjectLevel;
import com.beelinkers.englebee.general.domain.repository.MemberSubjectLevelRepository;
import com.beelinkers.englebee.general.domain.repository.SubjectLevelRepository;
import com.beelinkers.englebee.general.service.GeneralMemberService;
import com.beelinkers.englebee.teacher.exception.SubjectLevelNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final MemberRepository memberRepository;
  private final SubjectLevelRepository subjectLevelRepository;
  private final MemberSubjectLevelRepository memberSubjectLevelRepository;
  private final GeneralMemberService generalMemberService;

  @Override
  public Member signupStudent(SignupProgressSessionMember signupProgressSessionMember,
      StudentSignupRequestDTO studentSignupRequestDTO) {
    checkNicknameDuplicated(studentSignupRequestDTO.getNickname());

    Member student = Member.builder()
        .email(signupProgressSessionMember.getEmail())
        .role(Role.STUDENT)
        .loginType(signupProgressSessionMember.getLoginType())
        .nickname(studentSignupRequestDTO.getNickname())
        .grade(StudentGrade.fromKoreanGrade(studentSignupRequestDTO.getGrade()))
        .personalInfoCollectionAgreed(studentSignupRequestDTO.getPersonalInfoCollectionAgreed())
        .build();

    student = memberRepository.save(student);
    
    for (SubjectCode subjectCode : SubjectCode.values()) {
      SubjectLevel subjectLevel = subjectLevelRepository.findBySubjectCodeAndLevelCode(subjectCode,
              LevelCode.LOW)
          .orElseThrow(() -> new SubjectLevelNotFoundException("해당하는 과목 레벨이 존재하지 않습니다."));
      MemberSubjectLevel memberSubjectLevel = MemberSubjectLevel.builder()
          .student(student)
          .subjectLevel(subjectLevel)
          .build();

      memberSubjectLevelRepository.save(memberSubjectLevel);
    }
    log.info("학생 회원 가입 완료 : {}", student.getNickname());
    return student;
  }

  @Override
  public Member signupTeacher(SignupProgressSessionMember signupProgressSessionMember,
      TeacherSignupRequestDTO teacherSignupRequestDTO) {
    checkNicknameDuplicated(teacherSignupRequestDTO.getNickname());
    Member teacher = Member.builder()
        .email(signupProgressSessionMember.getEmail())
        .role(Role.TEACHER)
        .loginType(signupProgressSessionMember.getLoginType())
        .nickname(teacherSignupRequestDTO.getNickname())
        .grade(null)
        .personalInfoCollectionAgreed(teacherSignupRequestDTO.getPersonalInfoCollectionAgreed())
        .build();
    log.info("선생님 회원 가입 완료 : {}", teacher.getNickname());
    return memberRepository.save(teacher);
  }

  private void checkNicknameDuplicated(String nickname) {
    generalMemberService.checkNicknameDuplicated(nickname);
  }
}
