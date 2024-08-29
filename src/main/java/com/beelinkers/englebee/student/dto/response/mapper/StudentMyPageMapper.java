package com.beelinkers.englebee.student.dto.response.mapper;

import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.Question;
import com.beelinkers.englebee.student.dto.response.StudentMyPageCompletedExamDTO;
import com.beelinkers.englebee.student.dto.response.StudentMyPageCreatedExamDTO;
import com.beelinkers.englebee.student.dto.response.StudentMyPageWrittenQnaDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentMyPageMapper {

  // created-exam
  public StudentMyPageCreatedExamDTO studentMyPageCreatedExam(Exam exam) {
    String teacherNickName = exam.getLecture().getTeacher() != null ?
        exam.getLecture().getTeacher().getNickname() :
        "No Teacher";
    return new StudentMyPageCreatedExamDTO(
        exam.getSeq(),
        exam.getLecture().getSeq(),
        exam.getTitle(),
        exam.getStatus().name(),
        teacherNickName,
        exam.getCreatedAt()
    );
  }

  // completed-exam
  public StudentMyPageCompletedExamDTO studentMyPageCompletedExam(Exam exam) {
    String teacherNickName = exam.getLecture().getTeacher() != null ?
        exam.getLecture().getTeacher().getNickname() :
        "No Teacher";
    return new StudentMyPageCompletedExamDTO(
        exam.getSeq(),
        exam.getLecture().getSeq(),
        exam.getTitle(),
        exam.getStatus().name(),
        teacherNickName,
        exam.getCreatedAt()
    );
  }

  // written-qna
  public StudentMyPageWrittenQnaDTO studentMyPageWrittenQna(Question question) {
    return new StudentMyPageWrittenQnaDTO(
        question.getSeq(),
        question.getTitle(),
        question.getContent(),
        question.getCreatedAt()
    );
  }

}
