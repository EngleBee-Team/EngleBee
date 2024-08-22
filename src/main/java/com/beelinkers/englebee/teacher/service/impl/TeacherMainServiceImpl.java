package com.beelinkers.englebee.teacher.service.impl;

import com.beelinkers.englebee.general.domain.entity.LectureStatus;
import com.beelinkers.englebee.general.domain.repository.LectureRepository;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageLectureDTO;
import com.beelinkers.englebee.teacher.dto.response.mapper.TeacherMainPageMapper;
import com.beelinkers.englebee.teacher.service.TeacherMainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeacherMainServiceImpl implements TeacherMainService {

    private final LectureRepository lectureRepository;
    private final TeacherMainPageMapper teacherMainPageMapper;

    // lecture
    @Override
    public Page<TeacherMainPageLectureDTO> getLectureList(Long memberSeq, Pageable pageable) {
        return lectureRepository.findByTeacherSeqAndStatus(memberSeq, LectureStatus.CREATED, pageable)
                .map(teacherMainPageMapper::teacherMainPageLectureDto);
    }



}
