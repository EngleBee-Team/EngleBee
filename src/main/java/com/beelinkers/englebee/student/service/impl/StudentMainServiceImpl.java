package com.beelinkers.englebee.student.service.impl;

import com.beelinkers.englebee.general.domain.entity.ExamStatus;
import com.beelinkers.englebee.general.domain.entity.LectureStatus;
import com.beelinkers.englebee.general.domain.repository.ExamRepository;
import com.beelinkers.englebee.general.domain.repository.LectureRepository;
import com.beelinkers.englebee.student.dto.response.StudentMainPageLectureDTO;
import com.beelinkers.englebee.student.dto.response.StudentMainPageNewExamDTO;
import com.beelinkers.englebee.student.dto.response.StudentMainPageSubmitExamDTO;
import com.beelinkers.englebee.student.dto.response.mapper.StudentMainPageMapper;
import com.beelinkers.englebee.student.service.StudentMainService;
import java.util.List;
import java.util.stream.Collectors;
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
public class StudentMainServiceImpl implements StudentMainService {

  private final LectureRepository lectureRepository;
  private final ExamRepository examRepository;
  private final StudentMainPageMapper studentMainPageMapper;

  @Override
  @Transactional(readOnly = true)
  public Page<StudentMainPageLectureDTO> getLectureList(Long memberSeq, Pageable pageable) {
    return lectureRepository.findByStudentSeqAndStatus(memberSeq, LectureStatus.CREATED, pageable)
        .map(studentMainPageMapper::mainPageLectureDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<StudentMainPageNewExamDTO> getNewExamList(Long memberSeq, ExamStatus status) {
    return examRepository.findTop5ByLectureStudentSeqAndStatusOrderByCreatedAtDesc(memberSeq,
            status)
        .stream()
        .map(studentMainPageMapper::mainPageNewExamDTO).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<StudentMainPageSubmitExamDTO> getSubmitExamList(Long memberSeq,
      List<ExamStatus> status) {
    return examRepository.findTop5ByLectureStudentSeqAndStatusInOrderByCreatedAtDesc(
            memberSeq, status)
        .stream()
        .map(studentMainPageMapper::mainPageSubmitExamDTO)
        .collect(Collectors.toList());
  }

}
