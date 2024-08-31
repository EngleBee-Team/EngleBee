package com.beelinkers.englebee.general.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectLevelCodeDTO {

  private List<String> subjectCode;
  private List<String> levelCode;

}
