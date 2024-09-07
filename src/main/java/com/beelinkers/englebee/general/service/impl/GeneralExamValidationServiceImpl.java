package com.beelinkers.englebee.general.service.impl;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.auth.domain.repository.MemberRepository;
import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.ExamStatus;
import com.beelinkers.englebee.general.domain.repository.ExamRepository;
import com.beelinkers.englebee.general.exception.ExamNotFoundException;
import com.beelinkers.englebee.general.exception.InvalidExamStatusException;
import com.beelinkers.englebee.general.exception.InvalidMemberRoleException;
import com.beelinkers.englebee.general.exception.MemberNotFoundException;
import com.beelinkers.englebee.general.service.GeneralExamValidationService;
import com.beelinkers.englebee.student.exception.StudentIllegalAccessToExamException;
import com.beelinkers.englebee.teacher.exception.TeacherIllegalAccessToExamException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GeneralExamValidationServiceImpl implements GeneralExamValidationService {

  private final MemberRepository memberRepository;
  private final ExamRepository examRepository;

  @Override
  @Transactional(readOnly = true)
  public Member validateAndGetTeacher(Long teacherSeq) {
    Member teacher = memberRepository.findById(teacherSeq)
        .orElseThrow(() -> new MemberNotFoundException("해당하는 선생님 멤버가 존재하지 않습니다."));
    if (!teacher.isTeacher()) {
      throw new InvalidMemberRoleException("해당 멤버는 선생님 멤버가 아닙니다.");
    }
    return teacher;
  }

  @Override
  @Transactional(readOnly = true)
  public Member validateAndGetStudent(Long studentSeq) {
    Member student = memberRepository.findById(studentSeq)
        .orElseThrow(() -> new MemberNotFoundException("해당하는 학생 멤버가 존재하지 않습니다."));
    if (!student.isStudent()) {
      throw new InvalidMemberRoleException("해당 멤버는 학생 멤버가 아닙니다.");
    }
    return student;
  }

  @Override
  @Transactional(readOnly = true)
  public Exam validateAndGetExam(Long examSeq) {
    return examRepository.findById(examSeq)
        .orElseThrow(() -> new ExamNotFoundException("해당하는 시험이 존재하지 않습니다."));
  }

  @Override
  @Transactional(readOnly = true)
  public void validateTeacherAccessToExam(Member teacher, Exam exam) {
    if (!exam.getLecture().getTeacher().equals(teacher)) {
      throw new TeacherIllegalAccessToExamException("해당하는 시험은 선생님이 낸 시험이 아닙니다.");
    }
  }

  @Override
  @Transactional(readOnly = true)
  public void validateStudentAccessToExam(Member student, Exam exam) {
    if (!exam.getLecture().getStudent().equals(student)) {
      throw new StudentIllegalAccessToExamException("해당하는 시험은 현재 로그인한 학생이 접근할 수 있는 시험이 아닙니다.");
    }
  }

  @Override
  @Transactional(readOnly = true)
  public void validateExamIsReadyToBeSubmitted(Exam exam) {
    if (exam.getStatus() != ExamStatus.PREPARED) {
      throw new InvalidExamStatusException("시험을 제출할 수 없습니다 : 시험이 준비 상태가 아닙니다.");
    }
  }

  @Override
  @Transactional(readOnly = true)
  public void validateExamIsReadyToBeRegistered(Exam exam) {
    if (exam.getStatus() != ExamStatus.CREATED) {
      throw new InvalidExamStatusException("시험을 등록할 수 없습니다 : 시험이 생성 상태가 아닙니다.");
    }
  }

  @Override
  @Transactional(readOnly = true)
  public void validateExamIsReadyToBeFeedBacked(Exam exam) {
    if (exam.getStatus() != ExamStatus.SUBMITTED) {
      throw new InvalidExamStatusException("시험에 피드백을 할 수 없습니다 : 시험이 제출완료 상태가 아닙니다.");
    }
  }

}
