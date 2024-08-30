package com.beelinkers.englebee.admin.dto.response;

import com.beelinkers.englebee.general.domain.entity.LevelCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminMemberSubjectLevelCountDTO {
  private LevelCode levelCode;
  private Long count;
}
