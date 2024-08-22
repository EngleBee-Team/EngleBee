package com.beelinkers.englebee.teacher.dto.response.mapper;

import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.Lecture;
import com.beelinkers.englebee.general.domain.entity.Question;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageLectureDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageNewExamDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageQuestionDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeacherMainPageMapper {
    // main > lecture
    public TeacherMainPageLectureDTO teacherMainPageLectureDto(Lecture lecture) {
        List<List<String>> subjectLevelCode = lecture.getSubjectLevels().stream()
        .map( lectureSubjectLevel -> List.of(
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

    // main > Q&A
    public TeacherMainPageQuestionDTO teacherMainPageQuestionDto(Question question) {
        return new TeacherMainPageQuestionDTO(
                question.getSeq(),
                question.getMember().getNickname(),
                question.getTitle(),
                question.getCreatedAt()
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




}
