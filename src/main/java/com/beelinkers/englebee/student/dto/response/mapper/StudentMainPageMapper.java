package com.beelinkers.englebee.student.dto.response.mapper;

import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.Lecture;
import com.beelinkers.englebee.general.domain.entity.Question;
import com.beelinkers.englebee.student.dto.response.StudentMainPageSubmitExamDTO;
import com.beelinkers.englebee.student.dto.response.StudentMainPageLectureDTO;
import com.beelinkers.englebee.student.dto.response.StudentMainPageNewExamDTO;
import com.beelinkers.englebee.student.dto.response.StudentMainPageQuestionDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentMainPageMapper {

    // main > lecture
    public StudentMainPageLectureDTO mainPageLectureDto(Lecture lecture) {
        return new StudentMainPageLectureDTO(
            lecture.getSeq(),
            lecture.getTeacher().getSeq(),
            lecture.getTitle(),
            lecture.getStatus().name(),
            lecture.getCreatedAt()
        );
    }

    // main > Q&A
    public StudentMainPageQuestionDTO mainPageQuestionDTO(Question question) {
        return new StudentMainPageQuestionDTO(
            question.getSeq(),
            question.getMember().getSeq(),
            question.getTitle(),
            question.getCreatedAt()
        );
    }

    // main > newExamList
    public StudentMainPageNewExamDTO mainPageNewExamDTO(Exam exam) {
        return new StudentMainPageNewExamDTO(
            exam.getSeq(),
            exam.getLecture().getSeq(),
            exam.getStatus().name(),
            exam.getTitle()
        );
    }

    // main > submitExamList
    public StudentMainPageSubmitExamDTO mainPageSubmitExamDTO(Exam exam) {
        return new StudentMainPageSubmitExamDTO(
            exam.getSeq(),
            exam.getLecture().getSeq(),
            exam.getStatus().name(),
            exam.getTitle()
        );
    }
}
