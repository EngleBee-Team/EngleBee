package com.beelinkers.englebee.student.service;

import com.beelinkers.englebee.student.dto.response.StudentMyPageCompletedExamDTO;
import com.beelinkers.englebee.student.dto.response.StudentMyPageCreatedExamDTO;
import com.beelinkers.englebee.student.dto.response.StudentMyPageWrittenQnaDTO;
import com.beelinkers.englebee.student.dto.response.StudentMyPageWrittenReplyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentMyService {

  Page<StudentMyPageCreatedExamDTO> getStudentMyCreatedExamInfo(Long memberSeq, Pageable pageable);

  Page<StudentMyPageCompletedExamDTO> getStudentMyCompletedExamInfo(Long memberSeq,
      Pageable pageable);

  Page<StudentMyPageWrittenQnaDTO> getStudentMyWrittenQnaInfo(Long memberSeq, Pageable pageable);

  Page<StudentMyPageWrittenReplyDTO> getStudentMyWrittenReplyInfo(Long memberSeq,
      Pageable pageable);
}
