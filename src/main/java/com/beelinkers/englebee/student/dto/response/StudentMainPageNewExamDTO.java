package com.beelinkers.englebee.student.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentMainPageNewExamDTO {
    private Long seq;
    private Long lectureSeq;
    private String status;
    private String title;
    private String teacherNickname;
    private LocalDateTime createdAt;
}
