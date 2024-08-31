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
  private final ExamRepository examRepository;
  private final MemberSubjectLevelRepository memberSubjectLevelRepository;

  @Override
  @Transactional(readOnly = true)
  public TeacherExamRegisterPageDTO getTeacherExamRegisterPageInfo(Long teacherSeq, Long examSeq) {
    Member teacher = validateAndGetTeacher(teacherSeq);
    Exam exam = validateAndGetExam(examSeq);
    validateTeacherAccessToExam(teacher, exam);
    Lecture lecture = exam.getLecture();
    Map<String, String> lectureSubjectLevels = extractLectureSubjectLevels(lecture);
    Map<String, String> studentSubjectLevels = extractStudentSubjectLevels(lecture.getStudent());
    return new TeacherExamRegisterPageDTO(studentSubjectLevels, lectureSubjectLevels);
  }

  private Member validateAndGetTeacher(Long teacherSeq) {
    Member teacher = memberRepository.findById(teacherSeq)
        .orElseThrow(() -> new MemberNotFoundException("해당하는 선생님 멤버가 존재하지 않습니다."));
    if (!teacher.isTeacher()) {
      throw new InvalidMemberRoleException("해당 멤버는 선생님 멤버가 아닙니다.");
    }
    return teacher;
  }

  private Exam validateAndGetExam(Long examSeq) {
    return examRepository.findById(examSeq)
        .orElseThrow(() -> new ExamNotFoundException("해당하는 시험이 존재하지 않습니다."));
  }

  private void validateTeacherAccessToExam(Member teacher, Exam exam) {
    Lecture lecture = exam.getLecture();
    if (!lecture.getTeacher().equals(teacher)) {
      throw new TeacherIllegalAccessToExamException("해당하는 시험은 선생님이 낸 시험이 아닙니다.");
    }
  }

  private Map<String, String> extractLectureSubjectLevels(Lecture lecture) {
    Map<String, String> lectureSubjectLevels = new HashMap<>();
    lecture.getSubjectLevels().forEach(
        lectureSubjectLevel -> {
          SubjectLevel subjectLevel = lectureSubjectLevel.getSubjectLevel();
          lectureSubjectLevels.put(
              subjectLevel.getSubjectCode().getKoreanCode(),
              subjectLevel.getLevelCode().getKoreanCode()
          );
        }
    );
    return lectureSubjectLevels;
  }

  private Map<String, String> extractStudentSubjectLevels(Member student) {
    List<MemberSubjectLevel> memberSubjectLevels = memberSubjectLevelRepository.findByStudent(
        student);
    Map<String, String> studentSubjectLevels = new HashMap<>();
    memberSubjectLevels.forEach(
        memberSubjectLevel -> {
          SubjectLevel subjectLevel = memberSubjectLevel.getSubjectLevel();
          studentSubjectLevels.put(
              subjectLevel.getSubjectCode().getKoreanCode(),
              subjectLevel.getLevelCode().getKoreanCode()
          );
        }
    );
    return studentSubjectLevels;
  }
}
