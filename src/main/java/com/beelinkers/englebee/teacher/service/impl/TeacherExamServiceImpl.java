package com.beelinkers.englebee.teacher.service.impl;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.LevelCode;
import com.beelinkers.englebee.general.domain.entity.MemberSubjectLevel;
import com.beelinkers.englebee.general.domain.entity.SubjectCode;
import com.beelinkers.englebee.general.domain.entity.SubjectLevel;
import com.beelinkers.englebee.general.domain.repository.MemberSubjectLevelRepository;
import com.beelinkers.englebee.general.domain.repository.SubjectLevelRepository;
import com.beelinkers.englebee.general.exception.InvalidSubjectLevelCodeException;
import com.beelinkers.englebee.general.service.GeneralExamValidationService;
import com.beelinkers.englebee.teacher.domain.repository.TeacherQuestionRepository;
import com.beelinkers.englebee.teacher.dto.request.TeacherExamFeedbackRequestDTO;
import com.beelinkers.englebee.teacher.dto.request.TeacherExamRegisterRequestDTO;
import com.beelinkers.englebee.teacher.dto.request.TeacherQuestionCreateRequestDTO;
import com.beelinkers.englebee.teacher.dto.request.mapper.TeacherQuestionRequestMapper;
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

  private final GeneralExamValidationService teacherExamValidationService;
  private final TeacherQuestionRepository teacherQuestionRepository;
  private final MemberSubjectLevelRepository memberSubjectLevelRepository;
  private final SubjectLevelRepository subjectLevelRepository;
  private final TeacherQuestionRequestMapper teacherQuestionRequestMapper;

  @Override
  public void registerExam(Long teacherSeq, Long examSeq,
      TeacherExamRegisterRequestDTO teacherExamRegisterRequestDTO) {
    Member teacher = validateAndGetTeacher(teacherSeq);
    Exam exam = validateExamAndCheckIsPreparedForRegistration(teacher, examSeq);
    makeExamPrepared(exam, teacherExamRegisterRequestDTO);
  }

  @Override
  public void feedbackExam(Long teacherSeq, Long examSeq,
      TeacherExamFeedbackRequestDTO teacherExamFeedbackRequestDTO) {
    Member teacher = validateAndGetTeacher(teacherSeq);
    Exam exam = validateExamAndCheckIsPreparedForFeedback(teacher, examSeq);
    makeExamFeedbackCompleted(exam, teacherExamFeedbackRequestDTO);
  }

  private Member validateAndGetTeacher(Long teacherSeq) {
    return teacherExamValidationService.validateAndGetTeacher(teacherSeq);
  }

  private Exam validateExamAndCheckIsPreparedForRegistration(Member teacher, Long examSeq) {
    Exam exam = teacherExamValidationService.validateAndGetExam(examSeq);
    teacherExamValidationService.validateTeacherAccessToExam(teacher, exam);
    teacherExamValidationService.validateExamIsReadyToBeRegistered(exam);
    return exam;
  }

  private void makeExamPrepared(Exam exam,
      TeacherExamRegisterRequestDTO teacherExamRegisterRequestDTO) {
    exam.updateTitle(teacherExamRegisterRequestDTO.getTitle());
    List<TeacherQuestionCreateRequestDTO> teacherQuestionCreateRequestDTOs = teacherExamRegisterRequestDTO.getTeacherQuestionCreateRequestDTOs();
    teacherQuestionCreateRequestDTOs.stream()
        .map(teacherQuestionRequestMapper::toTeacherQuestion)
        .forEach(teacherQuestion -> {
          teacherQuestion.registerToExam(exam);
          teacherQuestionRepository.save(teacherQuestion);
        });
    exam.prepare();
  }

  private Exam validateExamAndCheckIsPreparedForFeedback(Member teacher, Long examSeq) {
    Exam exam = teacherExamValidationService.validateAndGetExam(examSeq);
    teacherExamValidationService.validateTeacherAccessToExam(teacher, exam);
    teacherExamValidationService.validateExamIsReadyToBeFeedBacked(exam);
    return exam;
  }

  private void updateStudentSubjectLevels(Member student,
      TeacherExamFeedbackRequestDTO feedbackRequestDTO) {
    memberSubjectLevelRepository.deleteByStudent(student);

    feedbackRequestDTO.getTeacherEvaluateStudentSubjectLevelDTOs().forEach(
        teacherLevelEvaluate -> {
          SubjectLevel subjectLevel = findSubjectLevel(
              teacherLevelEvaluate.getSubjectCode(),
              teacherLevelEvaluate.getLevelCode()
          );

          MemberSubjectLevel memberSubjectLevel = MemberSubjectLevel.builder()
              .student(student)
              .subjectLevel(subjectLevel)
              .build();

          memberSubjectLevelRepository.save(memberSubjectLevel);
        }
    );
  }

  private SubjectLevel findSubjectLevel(String subjectCodeStr, String levelCodeStr) {
    SubjectCode subjectCode = SubjectCode.fromKoreanCode(subjectCodeStr);
    LevelCode levelCode = LevelCode.fromKoreanCode(levelCodeStr);
    return subjectLevelRepository.findBySubjectCodeAndLevelCode(subjectCode, levelCode)
        .orElseThrow(() -> new InvalidSubjectLevelCodeException("해당하는 과목 레벨 수준이 존재하지 않습니다."));
  }

  private void makeExamFeedbackCompleted(Exam exam,
      TeacherExamFeedbackRequestDTO teacherExamFeedbackRequestDTO) {
    Member student = exam.getLecture().getStudent();
    updateStudentSubjectLevels(student, teacherExamFeedbackRequestDTO);
    exam.addTeacherFeedback(teacherExamFeedbackRequestDTO.getFeedback());
  }
}
