package com.beelinkers.englebee.general.service.impl;

import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.repository.ExamRepository;
import com.beelinkers.englebee.general.dto.response.ExamDetailPageDTO;
import com.beelinkers.englebee.general.dto.response.mapper.ExamDetailPageMapper;
import com.beelinkers.englebee.general.exception.ExamNotFoundException;
import com.beelinkers.englebee.general.service.ExamDetailPageService;
import com.beelinkers.englebee.teacher.domain.entity.TeacherQuestion;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ExamDetailPageServiceImpl implements ExamDetailPageService {

  private final ExamRepository examRepository;
  private final ExamDetailPageMapper examDetailPageMapper;

  @Override
  @Transactional(readOnly = true)
  public ExamDetailPageDTO getExamDetailPageDTO(Long examSeq) {
    Exam exam = examRepository.findWithQuestionsById(examSeq)
        .orElseThrow(() -> new ExamNotFoundException("해당하는 시험이 존재하지 않습니다."));
    List<TeacherQuestion> teacherQuestions = exam.getTeacherQuestions();
    return examDetailPageMapper.toExamDetailPageDTO(exam, teacherQuestions);
  }
}
