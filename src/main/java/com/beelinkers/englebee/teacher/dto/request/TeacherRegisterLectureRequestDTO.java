package com.beelinkers.englebee.teacher.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherRegisterLectureRequestDTO {

  private String studentNickname;
  private String letureTitle;
  private String subject;
  private String level;
}
