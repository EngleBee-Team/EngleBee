package com.beelinkers.englebee.student.dto.response.mapper;

import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.Lecture;
import com.beelinkers.englebee.general.domain.entity.Question;
import com.beelinkers.englebee.student.dto.response.MainPageSubmitExamDTO;
import com.beelinkers.englebee.student.dto.response.MainPageLectureDTO;
import com.beelinkers.englebee.student.dto.response.MainPageNewExamDTO;
import com.beelinkers.englebee.student.dto.response.MainPageQuestionDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentMainPageMapper {

    // main > lecture
    public MainPageLectureDTO mainPageLectureDto(Lecture lecture) {
        return new MainPageLectureDTO(
            lecture.getSeq(),
            lecture.getTeacher().getSeq(),
            lecture.getTitle(),
            lecture.getStatus().name(),
            lecture.getCreatedAt()
        );
    }

    // main > Q&A
    public MainPageQuestionDTO mainPageQuestionDTO(Question question) {
        return new MainPageQuestionDTO(
            question.getSeq(),
            question.getMember().getSeq(),
            question.getTitle(),
            question.getCreatedAt()
        );
    }

    // main > newExamList
    public MainPageNewExamDTO mainPageNewExamDTO(Exam exam) {
        return new MainPageNewExamDTO(
            exam.getSeq(),
            exam.getLecture().getSeq(),
            exam.getStatus().name(),
            exam.getTitle()
        );
    }

    // main > submitExamList
    public MainPageSubmitExamDTO mainPageSubmitExamDTO(Exam exam) {
        return new MainPageSubmitExamDTO(
            exam.getSeq(),
            exam.getLecture().getSeq(),
            exam.getStatus().name(),
            exam.getTitle()
        );
    }
}
