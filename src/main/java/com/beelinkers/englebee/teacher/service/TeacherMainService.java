package com.beelinkers.englebee.teacher.service;

import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageAuthoredExamDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageLectureDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageNewExamDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageQuestionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherMainService {
    Page<TeacherMainPageLectureDTO> getLectureList(Long memberSeq, Pageable pageable);
    Page<TeacherMainPageQuestionDTO> getQuestionList(Pageable pageable);
    Page<TeacherMainPageNewExamDTO> getNewExamList(Long memberSeq, Pageable pageable);
    Page<TeacherMainPageAuthoredExamDTO> getAuthoredExamList(Long memberSeq, Pageable pageable);
}
