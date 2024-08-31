package com.beelinkers.englebee.general.service;

import com.beelinkers.englebee.general.domain.entity.Lecture;

public interface ChatService {

  void finishLectureCreateExam(Long lectureSeq);
  private void createAndSaveExam(Lecture lecture){};
}
