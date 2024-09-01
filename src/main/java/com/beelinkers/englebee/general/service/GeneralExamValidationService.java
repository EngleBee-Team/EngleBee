package com.beelinkers.englebee.general.service;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.ExamStatus;

public interface GeneralExamValidationService {

  Member validateAndGetTeacher(Long teacherSeq);

  Exam validateAndGetExam(Long examSeq);

  void validateTeacherAccessToExam(Member teacher, Exam exam);

  void validateExamStatus(Exam exam, ExamStatus expectedStatus);
}
