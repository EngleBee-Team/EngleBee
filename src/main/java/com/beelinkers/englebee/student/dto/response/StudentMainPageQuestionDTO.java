package com.beelinkers.englebee.student.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentMainPageQuestionDTO {
    private Long seq;
    private String memberNickname;
    private String title;
    private LocalDateTime createdAt;
}
