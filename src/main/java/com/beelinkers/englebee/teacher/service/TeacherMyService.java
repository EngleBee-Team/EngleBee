package com.beelinkers.englebee.teacher.service;

import com.beelinkers.englebee.teacher.dto.response.TeacherMyPageExamHistoryDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMyPagePendingExamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherMyService {

  Page<TeacherMyPagePendingExamDTO> getTeacherMyPendingExamInfo(Long memberSeq, Pageable pageable);

  Page<TeacherMyPageExamHistoryDTO> getTeacherMyPageExamHistoryInfo(Long memberSeq,
      Pageable pageable);
}
