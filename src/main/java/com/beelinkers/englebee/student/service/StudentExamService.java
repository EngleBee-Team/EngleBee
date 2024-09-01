package com.beelinkers.englebee.student.service;

import com.beelinkers.englebee.student.dto.request.StudentExamSolveRequestDTO;

public interface StudentExamService {

  void solveExam(Long studentSeq, Long examSeq,
      StudentExamSolveRequestDTO studentExamSolveRequestDTO);
}
