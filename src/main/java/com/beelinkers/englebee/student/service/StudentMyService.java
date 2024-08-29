package com.beelinkers.englebee.student.service;

import com.beelinkers.englebee.student.dto.response.StudentMyPageCompletedExamDTO;
import com.beelinkers.englebee.student.dto.response.StudentMyPageCreatedExamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentMyService {

  Page<StudentMyPageCreatedExamDTO> getStudentMyCreatedExamInfo(Long memberSeq, Pageable pageable);

  Page<StudentMyPageCompletedExamDTO> getStudentMyCompletedExamInfo(Long memberSeq,
      Pageable pageable);
}
