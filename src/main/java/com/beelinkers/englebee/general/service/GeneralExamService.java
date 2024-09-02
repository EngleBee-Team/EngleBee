package com.beelinkers.englebee.general.service;

import com.beelinkers.englebee.general.domain.entity.ExamStatus;

public interface GeneralExamService {

  ExamStatus getExamStatus(Long examSeq);
}
