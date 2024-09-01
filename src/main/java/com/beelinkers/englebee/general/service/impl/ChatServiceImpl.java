package com.beelinkers.englebee.general.service.impl;

import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.Lecture;
import com.beelinkers.englebee.general.domain.repository.ExamRepository;
import com.beelinkers.englebee.general.domain.repository.LectureRepository;
import com.beelinkers.englebee.general.exception.InvalidLectureSeqException;
import com.beelinkers.englebee.general.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

  private final LectureRepository lectureRepository;
  private final ExamRepository examRepository;

  @Override
  public void finishLectureCreateExam(Long lectureSeq) {
    Lecture lecture = lectureRepository.findById(lectureSeq)
        .orElseThrow(()-> new InvalidLectureSeqException("잘못된 입력값입니다"));
    lecture.finish();

    createAndSaveExam(lecture);
  }

  public void createAndSaveExam(Lecture lecture) {
    // 4. Exam 객체 생성
    Exam exam = Exam.builder()
        .lecture(lecture)
        .title(lecture.getTitle() + " - Exam") // 적절한 제목 설정
        .build();

    // 5. Exam 저장
    examRepository.save(exam);
  }
}
