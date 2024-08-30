package com.beelinkers.englebee.teacher.dto.response.mapper;

import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.Question;
import com.beelinkers.englebee.teacher.dto.response.TeacherMyPageExamHistoryDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMyPagePendingExamDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMyPageWrittenQnaDTO;
import org.springframework.stereotype.Component;

@Component
public class TeacherMyPageMapper {

  public TeacherMyPagePendingExamDTO teacherMyPagePendingExam(Exam exam) {
    String studentNickName = exam.getLecture().getStudent() != null ?
        exam.getLecture().getStudent().getNickname() :
        "학생이 아닙니다.";
    return new TeacherMyPagePendingExamDTO(
        exam.getSeq(),
        exam.getLecture().getSeq(),
        exam.getTitle(),
        exam.getStatus().name(),
        studentNickName,
        exam.getCreatedAt()
    );
  }

  public TeacherMyPageExamHistoryDTO teacherMyPageExamHistory(Exam exam) {
    String studentNickName = exam.getLecture().getStudent() != null ?
        exam.getLecture().getStudent().getNickname() :
        "학생이 아닙니다.";
    return new TeacherMyPageExamHistoryDTO(
        exam.getSeq(),
        exam.getLecture().getSeq(),
        exam.getTitle(),
        exam.getStatus().name(),
        studentNickName,
        exam.getCreatedAt()
    );
  }

  public TeacherMyPageWrittenQnaDTO teacherMyPageWrittenQna(Question question) {
    return new TeacherMyPageWrittenQnaDTO(
        question.getSeq(),
        question.getTitle(),
        question.getContent(),
        question.getCreatedAt()
    );
  }
}
