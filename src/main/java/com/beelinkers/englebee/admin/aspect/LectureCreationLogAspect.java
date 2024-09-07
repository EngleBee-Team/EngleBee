package com.beelinkers.englebee.admin.aspect;

import com.beelinkers.englebee.admin.domain.entity.CreatedLectureLog;
import com.beelinkers.englebee.admin.domain.repository.CreatedLectureLogRepository;
import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.auth.domain.repository.MemberRepository;
import com.beelinkers.englebee.general.exception.MemberNotFoundException;
import com.beelinkers.englebee.teacher.dto.request.TeacherRegisterLectureRequestDTO;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LectureCreationLogAspect {

  private final CreatedLectureLogRepository lectureLogRepository;
  private final MemberRepository memberRepository;

  @Pointcut("execution(* com.beelinkers.englebee.general.service.LectureService.createLecture(..))")
  public void createLectureMethod() {}

  @AfterReturning(pointcut = "createLectureMethod() && args(teacherSeq, teacherRegisterLectureRequestDTO)")
  public void logCreateLecture(Long teacherSeq, TeacherRegisterLectureRequestDTO teacherRegisterLectureRequestDTO) {
    Member student = memberRepository.findByNickname(teacherRegisterLectureRequestDTO.getStudentNickname())
        .orElseThrow(()-> new MemberNotFoundException("해당 학생이 존재하지 않습니다"));

    CreatedLectureLog lectureLog = CreatedLectureLog.builder()
        .studentSeq(student.getSeq())
        .teacherSeq(teacherSeq)
        .build();

    lectureLogRepository.save(lectureLog);
  }
}
