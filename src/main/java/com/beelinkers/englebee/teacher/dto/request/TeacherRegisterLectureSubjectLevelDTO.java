package com.beelinkers.englebee.teacher.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherRegisterLectureSubjectLevelDTO {
  private String subject;
  private String level;
}
