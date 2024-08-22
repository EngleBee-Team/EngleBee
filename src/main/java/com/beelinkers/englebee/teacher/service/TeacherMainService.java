package com.beelinkers.englebee.teacher.service;

import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageLectureDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherMainService {
    Page<TeacherMainPageLectureDTO> getLectureList(Long memberSeq, Pageable pageable);
}
