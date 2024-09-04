package com.beelinkers.englebee.teacher.dto.response.mapper;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.teacher.dto.response.TeacherAccountPageResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class TeacherAccountPageMapper {

  public TeacherAccountPageResponseDTO teacherAccountResponseDTO(Member member) {
    return new TeacherAccountPageResponseDTO(
        member.getSeq(),
        member.getNickname()
    );
  }

}
