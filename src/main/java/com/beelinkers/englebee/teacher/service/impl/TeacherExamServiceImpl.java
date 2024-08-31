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
import com.beelinkers.englebee.teacher.domain.repository.TeacherQuestionRepository;
import com.beelinkers.englebee.teacher.dto.request.TeacherExamRegisterRequestDTO;
import com.beelinkers.englebee.teacher.dto.request.TeacherQuestionCreateRequestDTO;
import com.beelinkers.englebee.teacher.dto.request.mapper.TeacherQuestionRequestMapper;
import com.beelinkers.englebee.teacher.exception.TeacherIllegalAccessToExamException;
import com.beelinkers.englebee.teacher.service.TeacherExamService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeacherExamServiceImpl implements TeacherExamService {

  private final MemberRepository memberRepository;
  private final ExamRepository examRepository;
  private final TeacherQuestionRepository teacherQuestionRepository;
  private final TeacherQuestionRequestMapper teacherQuestionRequestMapper;

  @Override
  public void registerExam(Long teacherSeq, Long examSeq,
      TeacherExamRegisterRequestDTO teacherExamRegisterRequestDTO) {
    Member teacher = memberRepository.findById(teacherSeq)
        .orElseThrow(() -> new MemberNotFoundException("해당 멤버가 존재하지 않습니다."));
    Exam exam = examRepository.findById(examSeq)
        .orElseThrow(() -> new ExamNotFoundException("해당 시험이 존재하지 않습니다."));
    if (!exam.getStatus().equals(ExamStatus.CREATED)) {
      throw new InvalidExamStatusException("잘못된 시험 상태 값입니다.");
    }
    if (!teacher.isTeacher()) {
      throw new InvalidMemberRoleException("해당 멤버는 선생님 유저가 아닙니다.");
    }
    if (!exam.getLecture().getTeacher().equals(teacher)) {
      throw new TeacherIllegalAccessToExamException("해당하는 시험은 선생님이 낸 시험이 아닙니다.");
    }
    
    exam.prepare();
    List<TeacherQuestionCreateRequestDTO> teacherQuestionCreateRequestDTOs = teacherExamRegisterRequestDTO.getTeacherQuestionCreateRequestDTOs();

    teacherQuestionCreateRequestDTOs.stream()
        .map(teacherQuestionRequestMapper::toTeacherQuestion)
        .forEach(teacherQuestion -> {
          teacherQuestion.registerToExam(exam);
          teacherQuestionRepository.save(teacherQuestion);
        });
  }
}
