package com.beelinkers.englebee.student.service.impl;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.ExamStatus;
import com.beelinkers.englebee.general.service.GeneralExamValidationService;
import com.beelinkers.englebee.student.dto.response.StudentExamSolvePageDTO;
import com.beelinkers.englebee.student.dto.response.mapper.StudentExamSolvePageMapper;
import com.beelinkers.englebee.student.service.StudentExamPageService;
import com.beelinkers.englebee.teacher.domain.entity.TeacherQuestion;
import com.beelinkers.englebee.teacher.domain.repository.TeacherQuestionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentExamPageServiceImpl implements StudentExamPageService {

  private final GeneralExamValidationService studentExamValidationService;
  private final TeacherQuestionRepository teacherQuestionRepository;
  private final StudentExamSolvePageMapper studentExamSolvePageMapper;

  @Override
  @Transactional(readOnly = true)
  public StudentExamSolvePageDTO getStudentExamSolvePageDTO(Long studentSeq, Long examSeq) {
    Member student = studentExamValidationService.validateAndGetStudent(studentSeq);
    Exam exam = studentExamValidationService.validateAndGetExam(examSeq);
    studentExamValidationService.validateStudentAccessToExam(student, exam);
    studentExamValidationService.validateExamStatus(exam, ExamStatus.PREPARED);

    List<TeacherQuestion> teacherQuestions = teacherQuestionRepository.findByExam(exam);
    return studentExamSolvePageMapper.toExamSolvePageDTO(teacherQuestions);
  }

}
