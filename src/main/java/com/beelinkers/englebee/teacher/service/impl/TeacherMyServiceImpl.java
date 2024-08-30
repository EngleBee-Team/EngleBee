package com.beelinkers.englebee.teacher.service.impl;

import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.ExamStatus;
import com.beelinkers.englebee.general.domain.repository.ExamRepository;
import com.beelinkers.englebee.teacher.dto.response.TeacherMyPageExamHistoryDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMyPagePendingExamDTO;
import com.beelinkers.englebee.teacher.dto.response.mapper.TeacherMyPageMapper;
import com.beelinkers.englebee.teacher.service.TeacherMyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TeacherMyServiceImpl implements TeacherMyService {

  private final TeacherMyPageMapper teacherMyPageMapper;
  private final ExamRepository examRepository;

  @Override
  @Transactional(readOnly = true)
  public Page<TeacherMyPagePendingExamDTO> getTeacherMyPendingExamInfo(Long memberSeq,
      Pageable pageable) {
    Page<Exam> pendingExamList = examRepository.findByLectureTeacherSeqAndStatus(
        memberSeq, ExamStatus.CREATED, pageable
    );
    return pendingExamList.map(teacherMyPageMapper::teacherMyPagePendingExam);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<TeacherMyPageExamHistoryDTO> getTeacherMyPageExamHistoryInfo(Long memberSeq,
      Pageable pageable) {
    Page<Exam> examHistoryList = examRepository.findByLectureTeacherSeqAndStatusNot(
        memberSeq, ExamStatus.CREATED, pageable
    );
    return examHistoryList.map(teacherMyPageMapper::teacherMyPageExamHistory);
  }
}