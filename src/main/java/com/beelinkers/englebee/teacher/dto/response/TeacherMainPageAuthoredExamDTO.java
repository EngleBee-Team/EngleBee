package com.beelinkers.englebee.teacher.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherMainPageAuthoredExamDTO {
    private Long seq;
    private Long lectureSeq;
    private String studentNickname;
    private String title;
    private String status;
}
