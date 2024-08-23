package com.beelinkers.englebee.student.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentMainPageSubmitExamDTO {
    private Long seq;
    private Long lectureSeq;
    private String status;
    private String title;
}
