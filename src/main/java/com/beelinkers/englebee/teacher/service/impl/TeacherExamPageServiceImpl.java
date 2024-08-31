package com.beelinkers.englebee.teacher.service.impl;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.auth.domain.repository.MemberRepository;
import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.Lecture;
import com.beelinkers.englebee.general.domain.entity.MemberSubjectLevel;
import com.beelinkers.englebee.general.domain.entity.SubjectLevel;
import com.beelinkers.englebee.general.domain.repository.ExamRepository;
import com.beelinkers.englebee.general.domain.repository.MemberSubjectLevelRepository;
import com.beelinkers.englebee.general.exception.ExamNotFoundException;
import com.beelinkers.englebee.general.exception.InvalidMemberRoleException;
import com.beelinkers.englebee.general.exception.MemberNotFoundException;
import com.beelinkers.englebee.teacher.dto.response.TeacherExamRegisterPageDTO;
import com.beelinkers.englebee.teacher.exception.TeacherIllegalAccessToExamException;
import com.beelinkers.englebee.teacher.service.TeacherExamPageService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeacherExamPageServiceImpl implements TeacherExamPageService {

  private final MemberRepository memberRepository;
  private final MemberSubjectLevelRepository memberSubjectLevelRepository;
  private final ExamRepository examRepository;

  @Override
  @Transactional(readOnly = true)
  public TeacherExamRegisterPageDTO getTeacherExamRegisterPageInfo(Long teacherSeq, Long examSeq) {
    Member teacher = memberRepository.findById(teacherSeq)
        .orElseThrow(() -> new MemberNotFoundException("해당하는 선생님 멤버가 존재하지 않습니다."));

    Exam exam = examRepository.findById(examSeq)
        .orElseThrow(() -> new ExamNotFoundException("해당하는 시험이 존재하지 않습니다."));

    if (!teacher.isTeacher()) {
      throw new InvalidMemberRoleException("해당 멤버는 선생님 멤버가 아닙니다.");
    }

    Lecture lecture = exam.getLecture();
    if (!lecture.getTeacher().equals(teacher)) {
      throw new TeacherIllegalAccessToExamException("해당하는 시험은 선생님이 낸 시험이 아닙니다.");
    }

    Member student = lecture.getStudent();
    List<MemberSubjectLevel> memberSubjectLevels = memberSubjectLevelRepository.findByStudent(
        student);
    Map<String, String> studentSubjectLevels = new HashMap<>();
    memberSubjectLevels.forEach(
        memberSubjectLevel -> {
          System.out.println(
              "========================memberSubjectLevel로 인한 SELECT문 호출 ========================");
          SubjectLevel subjectLevel = memberSubjectLevel.getSubjectLevel();
          studentSubjectLevels.put(subjectLevel.getSubjectKoreanCode(),
              subjectLevel.getLevelKoreanCode());
        }
    );

    Map<String, String> lectureSubjectLevels = new HashMap<>();
    lecture.getSubjectLevels().forEach(
        lectureSubjectLevel -> {
          SubjectLevel subjectLevel = lectureSubjectLevel.getSubjectLevel();
          lectureSubjectLevels.put(subjectLevel.getSubjectKoreanCode(),
              subjectLevel.getLevelKoreanCode());
        }
    );

    System.out.println("studentSubjectLevels = " + studentSubjectLevels);

    return new TeacherExamRegisterPageDTO(studentSubjectLevels, lectureSubjectLevels);
  }
}
