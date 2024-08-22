package com.beelinkers.englebee.student.service;

import com.beelinkers.englebee.student.dto.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentMainService {
    Page<MainPageLectureDTO> getLectureList(Long memberSeq, Pageable pageable);
    Page<MainPageQuestionDTO> getQuestionList(Pageable pageable);
    Page<MainPageNewExamDTO> getNewExamList(Long memberSeq, Pageable pageable);
    Page<MainPageSubmitExamDTO> getSubmitExamList(Long memberSeq, Pageable pageable);

}
