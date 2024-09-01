package com.beelinkers.englebee.student.service;

import com.beelinkers.englebee.student.dto.request.StudentExamSubmitRequestDTO;

public interface StudentExamService {

  void submitExam(Long studentSeq, Long examSeq,
      StudentExamSubmitRequestDTO studentExamSubmitRequestDTO);
}
