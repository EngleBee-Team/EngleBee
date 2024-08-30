package com.beelinkers.englebee.admin.dto.response;

import com.beelinkers.englebee.auth.domain.entity.StudentGrade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminMemberAgeGroupCountDTO {
  private StudentGrade studentGrade;
  private Long count;
}
