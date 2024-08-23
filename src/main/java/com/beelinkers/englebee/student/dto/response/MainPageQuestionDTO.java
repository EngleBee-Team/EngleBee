package com.beelinkers.englebee.student.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MainPageQuestionDTO {
    private Long seq;
    private Long memberSeq;
    private String title;
    private LocalDateTime createdAt;
}
