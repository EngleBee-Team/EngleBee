package com.beelinkers.englebee.student.service;

import com.beelinkers.englebee.student.dto.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentMainService {
    Page<StudentMainPageLectureDTO> getLectureList(Long memberSeq, Pageable pageable);
    Page<StudentMainPageQuestionDTO> getQuestionList(Pageable pageable);
    Page<StudentMainPageNewExamDTO> getNewExamList(Long memberSeq, Pageable pageable);
    Page<StudentMainPageSubmitExamDTO> getSubmitExamList(Long memberSeq, Pageable pageable);

}
