package com.beelinkers.englebee.teacher.service;

import com.beelinkers.englebee.teacher.dto.request.TeacherExamRegisterRequestDTO;

public interface TeacherExamService {

  void registerExam(Long teacherSeq, Long examSeq,
      TeacherExamRegisterRequestDTO teacherExamRegisterRequestDTO);
}
