package com.beelinkers.englebee.student.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MainPageLectureDTO {
    private Long id;
    private Long teacherSeq;
    private String title;
    private String status;
    private LocalDateTime createdAt;

}
