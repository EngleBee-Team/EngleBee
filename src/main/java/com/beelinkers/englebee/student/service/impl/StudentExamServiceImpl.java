package com.beelinkers.englebee.student.service.impl;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.service.GeneralExamValidationService;
import com.beelinkers.englebee.student.dto.request.StudentAnswerToTeacherQuestionDTO;
import com.beelinkers.englebee.student.dto.request.StudentExamSubmitRequestDTO;
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
  public void submitExam(Long studentSeq, Long examSeq,
      StudentExamSubmitRequestDTO studentExamSubmitRequestDTO) {
    Member student = validateAndGetStudent(studentSeq);
    Exam exam = validateExamAndCheckIsPreparedForSubmitted(student, examSeq);
    makeExamSubmitted(studentExamSubmitRequestDTO, exam);
  }

  private Member validateAndGetStudent(Long studentSeq) {
    return studentExamValidationService.validateAndGetStudent(studentSeq);
  }

  private Exam validateExamAndCheckIsPreparedForSubmitted(Member student, Long examSeq) {
    Exam exam = studentExamValidationService.validateAndGetExam(examSeq);
    studentExamValidationService.validateStudentAccessToExam(student, exam);
    studentExamValidationService.validateExamIsReadyToBeSubmitted(exam);
    return exam;
  }

  private void makeExamSubmitted(StudentExamSubmitRequestDTO studentExamSubmitRequestDTO,
      Exam exam) {
    answerToTeacherQuestions(studentExamSubmitRequestDTO);
    exam.submit();
  }

  private void answerToTeacherQuestions(StudentExamSubmitRequestDTO studentExamSubmitRequestDTO) {
    List<TeacherQuestion> teacherQuestions = findExamTeacherQuestions(
        studentExamSubmitRequestDTO);

    studentExamSubmitRequestDTO.getStudentAnswerToTeacherQuestionDTOs().forEach(dto -> {
      TeacherQuestion teacherQuestion = teacherQuestions.stream()
          .filter(question -> question.getSeq().equals(dto.getTeacherQuestionSeq()))
          .findFirst()
          .orElseThrow(() -> new TeacherQuestionNotFoundException("해당하는 선생님 질문이 존재하지 않습니다."));
      teacherQuestion.insertStudentAnswer(dto.getStudentAnswer());
    });
  }

  private List<TeacherQuestion> findExamTeacherQuestions(
      StudentExamSubmitRequestDTO studentExamSubmitRequestDTO) {
    List<Long> teacherQuestionSeqs = studentExamSubmitRequestDTO.getStudentAnswerToTeacherQuestionDTOs()
        .stream()
        .map(StudentAnswerToTeacherQuestionDTO::getTeacherQuestionSeq)
        .toList();
    return teacherQuestionRepository.findAllById(
        teacherQuestionSeqs);
  }
}
