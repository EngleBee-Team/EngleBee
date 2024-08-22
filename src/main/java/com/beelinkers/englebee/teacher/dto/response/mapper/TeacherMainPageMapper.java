package com.beelinkers.englebee.teacher.dto.response.mapper;

import com.beelinkers.englebee.general.domain.entity.Lecture;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageLectureDTO;
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



}
