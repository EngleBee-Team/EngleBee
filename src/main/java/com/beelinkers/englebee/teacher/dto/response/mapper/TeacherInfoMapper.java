package com.beelinkers.englebee.teacher.dto.response.mapper;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.teacher.dto.response.TeacherInfoDTO;
import org.springframework.stereotype.Component;

@Component
public class TeacherInfoMapper {

  public TeacherInfoDTO teacherInfo(Member member) {
    return new TeacherInfoDTO(
        member.getSeq(),
        member.getNickname()
    );
  }
}
