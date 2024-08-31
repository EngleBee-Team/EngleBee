package com.beelinkers.englebee.teacher.dto.response.mapper;

import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.Lecture;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageAuthoredExamDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageLectureDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageNewExamDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TeacherMainPageMapper {

  // main > lecture
  public TeacherMainPageLectureDTO teacherMainPageLectureDto(Lecture lecture) {
    List<List<String>> subjectLevelCode = lecture.getSubjectLevels().stream()
        .map(lectureSubjectLevel -> List.of(
            lectureSubjectLevel.getSubjectLevel().getSubjectCode().getKoreanCode(),
            lectureSubjectLevel.getSubjectLevel().getLevelCode().getKoreanCode()
        ))
        .collect(Collectors.toList());
    return new TeacherMainPageLectureDTO(
        lecture.getSeq(),
        lecture.getStudent().getNickname(),
        lecture.getTitle(),
        lecture.getStatus().name(),
        lecture.getCreatedAt(),
        subjectLevelCode
    );
  }

  // main > new-exam
  public TeacherMainPageNewExamDTO teacherMainPageNewExamDto(Exam exam) {
    String studentNickname = exam.getLecture().getStudent().getNickname();
    return new TeacherMainPageNewExamDTO(
        exam.getSeq(),
        exam.getLecture().getSeq(),
        studentNickname,
        exam.getTitle(),
        exam.getStatus().name()
    );
  }

  // main > authored-exam
  public TeacherMainPageAuthoredExamDTO teacherMainPageAuthoredExamDTO(Exam exam) {
    String studentNickname = exam.getLecture().getStudent().getNickname();
    return new TeacherMainPageAuthoredExamDTO(
        exam.getSeq(),
        exam.getLecture().getSeq(),
        studentNickname,
        exam.getTitle(),
        exam.getStatus().name()
    );
  }

}
