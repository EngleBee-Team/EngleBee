package com.beelinkers.englebee.student.service;

import com.beelinkers.englebee.general.domain.entity.ExamStatus;
import com.beelinkers.englebee.student.dto.response.StudentMainPageLectureDTO;
import com.beelinkers.englebee.student.dto.response.StudentMainPageNewExamDTO;
import com.beelinkers.englebee.student.dto.response.StudentMainPageSubmitExamDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentMainService {

  Page<StudentMainPageLectureDTO> getLectureList(Long memberSeq, Pageable pageable);

  List<StudentMainPageNewExamDTO> getNewExamList(Long memberSeq, ExamStatus status);

  List<StudentMainPageSubmitExamDTO> getSubmitExamList(Long memberSeq, List<ExamStatus> status);

}
