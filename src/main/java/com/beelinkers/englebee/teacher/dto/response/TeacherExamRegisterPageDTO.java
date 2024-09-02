package com.beelinkers.englebee.teacher.dto.response;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeacherExamRegisterPageDTO {

  private Map<String, String> lectureSubjectLevels;
  private Map<String, String> studentSubjectLevels;

}
