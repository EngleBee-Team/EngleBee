package com.beelinkers.englebee.student.service;

import com.beelinkers.englebee.student.dto.response.StudentExamSolvePageDTO;

public interface StudentExamPageService {

  StudentExamSolvePageDTO getStudentExamSolvePageDTO(Long studentSeq, Long examSeq);
}
