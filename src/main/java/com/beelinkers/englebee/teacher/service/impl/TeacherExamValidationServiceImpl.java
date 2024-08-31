package com.beelinkers.englebee.teacher.service.impl;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.auth.domain.repository.MemberRepository;
import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.ExamStatus;
import com.beelinkers.englebee.general.domain.repository.ExamRepository;
import com.beelinkers.englebee.general.exception.ExamNotFoundException;
import com.beelinkers.englebee.general.exception.InvalidExamStatusException;
import com.beelinkers.englebee.general.exception.InvalidMemberRoleException;
import com.beelinkers.englebee.general.exception.MemberNotFoundException;
import com.beelinkers.englebee.teacher.exception.TeacherIllegalAccessToExamException;
import com.beelinkers.englebee.teacher.service.TeacherExamValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeacherExamValidationServiceImpl implements TeacherExamValidationService {

  private final MemberRepository memberRepository;
  private final ExamRepository examRepository;

  @Transactional(readOnly = true)
  public Member validateAndGetTeacher(Long teacherSeq) {
    Member teacher = memberRepository.findById(teacherSeq)
        .orElseThrow(() -> new MemberNotFoundException("해당하는 선생님 멤버가 존재하지 않습니다."));
    if (!teacher.isTeacher()) {
      throw new InvalidMemberRoleException("해당 멤버는 선생님 멤버가 아닙니다.");
    }
    return teacher;
  }

  @Transactional(readOnly = true)
  public Exam validateAndGetExam(Long examSeq) {
    return examRepository.findById(examSeq)
        .orElseThrow(() -> new ExamNotFoundException("해당하는 시험이 존재하지 않습니다."));
  }

  @Transactional(readOnly = true)
  public void validateTeacherAccessToExam(Member teacher, Exam exam) {
    if (!exam.getLecture().getTeacher().equals(teacher)) {
      throw new TeacherIllegalAccessToExamException("해당하는 시험은 선생님이 낸 시험이 아닙니다.");
    }
  }

  @Transactional(readOnly = true)
  public void validateExamStatus(Exam exam, ExamStatus expectedStatus) {
    if (!exam.getStatus().equals(expectedStatus)) {
      throw new InvalidExamStatusException("잘못된 시험 상태 값입니다. ExamStatus : " + expectedStatus);
    }
  }

}
