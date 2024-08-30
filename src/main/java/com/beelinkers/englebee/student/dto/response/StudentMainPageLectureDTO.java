package com.beelinkers.englebee.student.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentMainPageLectureDTO {
    private Long id;
    private String teacherNickname;
    private String title;
    private String status;
    private LocalDateTime createdAt;
    private List<List<String>> subjectLevelCode;
}
