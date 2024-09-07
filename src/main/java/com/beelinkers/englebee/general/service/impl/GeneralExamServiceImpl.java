package com.beelinkers.englebee.general.service.impl;

import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.repository.ExamRepository;
import com.beelinkers.englebee.general.exception.ExamNotFoundException;
import com.beelinkers.englebee.general.service.GeneralExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class GeneralExamServiceImpl implements GeneralExamService {

  private final ExamRepository examRepository;

  @Override
  @Transactional(readOnly = true)
  public Exam findExam(Long examSeq) {
    return examRepository.findById(examSeq)
        .orElseThrow(() -> new ExamNotFoundException("해당하는 시험이 존재하지 않습니다."));
  }
}
