package com.beelinkers.englebee.student.dto.response.mapper;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.student.dto.response.StudentAccountPageResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentAccountPageMapper {

  public StudentAccountPageResponseDTO studentAccountResponseDTO(Member member) {
    return new StudentAccountPageResponseDTO(
        member.getSeq(),
        member.getNickname(),
        member.getGrade().getKoreanGrade()
    );
  }

}
