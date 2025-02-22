package com.beelinkers.englebee.student.service;

import com.beelinkers.englebee.general.domain.entity.ExamStatus;
import com.beelinkers.englebee.general.domain.entity.LectureStatus;
import com.beelinkers.englebee.student.dto.response.StudentMainPageLectureDTO;
import com.beelinkers.englebee.student.dto.response.StudentMainPageNewExamDTO;
import com.beelinkers.englebee.student.dto.response.StudentMainPageSubmitExamDTO;
import java.util.List;

public interface StudentMainService {

  List<StudentMainPageLectureDTO> getOngoingLectureInfo(Long memberSeq, Long lectureSeq,
      LectureStatus lectureStatus);

  List<StudentMainPageNewExamDTO> getPreparedExamInfo(Long memberSeq, ExamStatus status);

  List<StudentMainPageSubmitExamDTO> getCompletedExamInfo(Long memberSeq,
      List<ExamStatus> status);

}
