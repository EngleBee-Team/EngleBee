package com.beelinkers.englebee.teacher.service;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.ExamStatus;

public interface TeacherExamValidationService {

  Member validateAndGetTeacher(Long teacherSeq);

  Exam validateAndGetExam(Long examSeq);

  void validateTeacherAccessToExam(Member teacher, Exam exam);

  void validateExamStatus(Exam exam, ExamStatus expectedStatus);
}
