package com.beelinkers.englebee.teacher.service.impl;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.Lecture;
import com.beelinkers.englebee.general.domain.entity.LectureSubjectLevel;
import com.beelinkers.englebee.general.domain.entity.MemberSubjectLevel;
import com.beelinkers.englebee.general.domain.entity.SubjectLevel;
import com.beelinkers.englebee.general.domain.repository.LectureSubjectLevelRepository;
import com.beelinkers.englebee.general.domain.repository.MemberSubjectLevelRepository;
import com.beelinkers.englebee.teacher.dto.response.TeacherExamRegisterPageDTO;
import com.beelinkers.englebee.teacher.service.TeacherExamPageService;
import com.beelinkers.englebee.teacher.service.TeacherExamValidationService;
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

  private final TeacherExamValidationService validationService;
  private final MemberSubjectLevelRepository memberSubjectLevelRepository;
  private final LectureSubjectLevelRepository lectureSubjectLevelRepository;

  @Override
  @Transactional(readOnly = true)
  public TeacherExamRegisterPageDTO getTeacherExamRegisterPageInfo(Long teacherSeq, Long examSeq) {
    Member teacher = validationService.validateAndGetTeacher(teacherSeq);
    Exam exam = validationService.validateAndGetExam(examSeq);
    validationService.validateTeacherAccessToExam(teacher, exam);

    Lecture lecture = exam.getLecture();
    Map<String, String> lectureSubjectLevels = extractLectureSubjectLevels(lecture);
    Map<String, String> studentSubjectLevels = extractStudentSubjectLevels(lecture.getStudent());
    return new TeacherExamRegisterPageDTO(studentSubjectLevels, lectureSubjectLevels);
  }

  private Map<String, String> extractLectureSubjectLevels(Lecture lecture) {
    List<LectureSubjectLevel> lectureSubjectLevels =
        lectureSubjectLevelRepository.findByLectureWithSubjectLevel(lecture);
    Map<String, String> lectureSubjectLevelsMap = new HashMap<>();
    lectureSubjectLevels.forEach(
        lectureSubjectLevel -> {
          SubjectLevel subjectLevel = lectureSubjectLevel.getSubjectLevel();
          lectureSubjectLevelsMap.put(
              subjectLevel.getSubjectCode().getKoreanCode(),
              subjectLevel.getLevelCode().getKoreanCode()
          );
        }
    );
    return lectureSubjectLevelsMap;
  }

  private Map<String, String> extractStudentSubjectLevels(Member student) {
    List<MemberSubjectLevel> studentSubjectLevels = memberSubjectLevelRepository.findByStudentWithSubjectLevel(
        student);
    Map<String, String> studentSubjectLevelsMap = new HashMap<>();
    studentSubjectLevels.forEach(
        memberSubjectLevel -> {
          SubjectLevel subjectLevel = memberSubjectLevel.getSubjectLevel();
          studentSubjectLevelsMap.put(
              subjectLevel.getSubjectCode().getKoreanCode(),
              subjectLevel.getLevelCode().getKoreanCode()
          );
        }
    );
    return studentSubjectLevelsMap;
  }
}
