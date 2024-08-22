package com.beelinkers.englebee.teacher.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherMainPageLectureDTO {
    private Long id;
    private String studentNickname;
    private String title;
    private String status;
    private LocalDateTime createdAt;
    private List<List<String>> subjectLevelCode;
}
