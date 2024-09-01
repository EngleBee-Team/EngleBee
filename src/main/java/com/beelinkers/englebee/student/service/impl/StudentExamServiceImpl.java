package com.beelinkers.englebee.student.service.impl;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.service.GeneralExamValidationService;
import com.beelinkers.englebee.student.dto.request.StudentAnswerToTeacherQuestionDTO;
import com.beelinkers.englebee.student.dto.request.StudentExamSolveRequestDTO;
import com.beelinkers.englebee.student.service.StudentExamService;
import com.beelinkers.englebee.teacher.domain.entity.TeacherQuestion;
import com.beelinkers.englebee.teacher.domain.repository.TeacherQuestionRepository;
import com.beelinkers.englebee.teacher.exception.TeacherQuestionNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentExamServiceImpl implements StudentExamService {

  private final GeneralExamValidationService studentExamValidationService;
  private final TeacherQuestionRepository teacherQuestionRepository;

  @Override
  public void solveExam(Long studentSeq, Long examSeq,
      StudentExamSolveRequestDTO studentExamSolveRequestDTO) {
    Member student = studentExamValidationService.validateAndGetStudent(studentSeq);
    Exam exam = studentExamValidationService.validateAndGetExam(examSeq);
    studentExamValidationService.validateStudentAccessToExam(student, exam);
    studentExamValidationService.validateExamIsReadyToBeSolved(exam);

    List<Long> teacherQuestionSeqs = studentExamSolveRequestDTO.getStudentAnswerToTeacherQuestionDTOs()
        .stream()
        .map(StudentAnswerToTeacherQuestionDTO::getTeacherQuestionSeq)
        .toList();

    List<TeacherQuestion> teacherQuestions = teacherQuestionRepository.findAllById(
        teacherQuestionSeqs);

    studentExamSolveRequestDTO.getStudentAnswerToTeacherQuestionDTOs().forEach(dto -> {
      TeacherQuestion teacherQuestion = teacherQuestions.stream()
          .filter(question -> question.getSeq().equals(dto.getTeacherQuestionSeq()))
          .findFirst()
          .orElseThrow(() -> new TeacherQuestionNotFoundException("해당하는 선생님 질문이 존재하지 않습니다."));
      teacherQuestion.insertStudentAnswer(dto.getStudentAnswer());
    });
    
    exam.submit();
  }
}
