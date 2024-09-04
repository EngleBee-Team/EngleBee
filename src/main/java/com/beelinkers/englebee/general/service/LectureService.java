package com.beelinkers.englebee.general.service;

import com.beelinkers.englebee.teacher.dto.request.TeacherRegisterLectureRequestDTO;

public interface LectureService {

  void createLecture(Long teacherSeq, TeacherRegisterLectureRequestDTO teacherRegisterLectureRequestDTO);
}
