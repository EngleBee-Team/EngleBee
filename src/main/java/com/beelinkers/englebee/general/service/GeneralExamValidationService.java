package com.beelinkers.englebee.general.service;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.general.domain.entity.Exam;

public interface GeneralExamValidationService {

  Member validateAndGetTeacher(Long teacherSeq);

  Member validateAndGetStudent(Long studentSeq);

  Exam validateAndGetExam(Long examSeq);

  void validateTeacherAccessToExam(Member teacher, Exam exam);

  void validateStudentAccessToExam(Member student, Exam exam);

  void validateExamIsReadyToBeSubmitted(Exam exam);

  void validateExamIsReadyToBeRegistered(Exam exam);

  void validateExamIsReadyToBeFeedBacked(Exam exam);
}
