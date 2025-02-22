package com.beelinkers.englebee.teacher.service;

import com.beelinkers.englebee.teacher.dto.response.TeacherExamFeedbackPageDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherExamRegisterPageDTO;

public interface TeacherExamPageService {

  TeacherExamRegisterPageDTO getTeacherExamRegisterPageInfo(Long teacherSeq, Long examSeq);

  TeacherExamFeedbackPageDTO getTeacherExamFeedbackPageInfo(Long teacherSeq, Long examSeq);

}
