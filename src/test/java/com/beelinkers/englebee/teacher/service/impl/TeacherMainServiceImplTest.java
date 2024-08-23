package com.beelinkers.englebee.teacher.service.impl;

import com.beelinkers.englebee.auth.domain.entity.LoginType;
import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.auth.domain.entity.Role;
import com.beelinkers.englebee.general.domain.entity.*;
import com.beelinkers.englebee.general.domain.repository.ExamRepository;
import com.beelinkers.englebee.general.domain.repository.LectureRepository;
import com.beelinkers.englebee.general.domain.repository.QuestionRepository;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageAuthoredExamDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageLectureDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageNewExamDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageQuestionDTO;
import com.beelinkers.englebee.teacher.dto.response.mapper.TeacherMainPageMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeacherMainServiceImplTest {

    @Mock
    private TeacherMainPageMapper teacherMainPageMapper;

    @Mock
    private LectureRepository lectureRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private ExamRepository examRepository;

    @InjectMocks
    private TeacherMainServiceImpl teacherMainService;

    @Test
    void testGetLectureList() {
        // Given
        Long memberSeq = 3L;
        PageRequest pageable = PageRequest.of(0, 10);

        Member teacher = new Member("user3", "user3@example.com", Role.TEACHER, null, LoginType.GOOGLE, true);
        Member student = new Member("user2", "user2@example.com", Role.STUDENT, null, LoginType.GOOGLE, true);

        Lecture lecture = Lecture.builder()
                .teacher(teacher)
                .student(student)
                .title("기초 문법 강의")
                .build();

        Page<Lecture> lecturePage = new PageImpl<>(List.of(lecture));
        TeacherMainPageLectureDTO lectureDTO = new TeacherMainPageLectureDTO(
                1L, "user2", "기초 문법 강의", "CREATED", LocalDateTime.now(), List.of()
        );

        when(lectureRepository.findByTeacherSeqAndStatus(anyLong(), eq(LectureStatus.CREATED), any(PageRequest.class)))
                .thenReturn(lecturePage);
        when(teacherMainPageMapper.teacherMainPageLectureDto(lecture)).thenReturn(lectureDTO);

        // When
        Page<TeacherMainPageLectureDTO> result = teacherMainService.getLectureList(memberSeq, pageable);

        // Then
        assertEquals(1, result.getTotalElements());
        assertEquals(lectureDTO, result.getContent().get(0));
    }

    @Test
    void testGetQuestionList() {
        // Given
        PageRequest pageable = PageRequest.of(0, 10);
        Member member = new Member("user3", "user3@example.com", Role.TEACHER, null, LoginType.GOOGLE, true);
        Question question = new Question(member, "What is Java?", "Detailed description");

        Page<Question> questionPage = new PageImpl<>(List.of(question));
        when(questionRepository.findAll(pageable)).thenReturn(questionPage);

        TeacherMainPageQuestionDTO questionDTO = new TeacherMainPageQuestionDTO(
                3L, "user3", "What is Java?", LocalDateTime.now()
        );
        when(teacherMainPageMapper.teacherMainPageQuestionDto(question)).thenReturn(questionDTO);

        // When
        Page<TeacherMainPageQuestionDTO> result = teacherMainService.getQuestionList(pageable);

        // Then
        assertEquals(1, result.getTotalElements());
        assertEquals("user3", result.getContent().get(0).getMemberNickname());
        assertEquals("What is Java?", result.getContent().get(0).getTitle());
    }

    @Test
    void testGetNewExamList() {
        // Given
        Long memberSeq = 1L;
        PageRequest pageable = PageRequest.of(0, 10);

        Member teacher = new Member("teacher1", "teacher1@example.com", Role.TEACHER, null, LoginType.GOOGLE, true);
        Member student = new Member("student1", "student1@example.com", Role.STUDENT, null, LoginType.GOOGLE, true);

        Lecture lecture = Lecture.builder()
                .teacher(teacher)
                .student(student)
                .title("Lecture 1")
                .build();

        Exam exam = Exam.builder()
                .lecture(lecture)
                .title("Exam 1")
                .build();

        Page<Exam> examPage = new PageImpl<>(List.of(exam));

        TeacherMainPageNewExamDTO newExamDTO = new TeacherMainPageNewExamDTO(
                1L, 1L, "user2", "Exam 1", "CREATED"
        );

        when(examRepository.findByLectureTeacherSeqAndStatus(memberSeq, ExamStatus.CREATED, pageable))
                .thenReturn(examPage);
        when(teacherMainPageMapper.teacherMainPageNewExamDto(exam)).thenReturn(newExamDTO);

        // When
        Page<TeacherMainPageNewExamDTO> result = teacherMainService.getNewExamList(memberSeq, pageable);

        // Then
        assertEquals(1, result.getTotalElements());
        assertEquals(newExamDTO, result.getContent().get(0));
    }

    @Test
    void testGetAuthoredExamList() {
        // Given
        Long memberSeq = 1L;
        PageRequest pageable = PageRequest.of(0, 10);

        Member teacher = new Member("user6", "user3@example.com", Role.TEACHER, null, LoginType.GOOGLE, true);
        Member student = new Member("user4", "user2@example.com", Role.STUDENT, null, LoginType.GOOGLE, true);

        Lecture lecture = Lecture.builder()
                .teacher(teacher)
                .student(student)
                .title("Lecture 1")
                .build();

        Exam exam = Exam.builder()
                .lecture(lecture)
                .title("Exam 1")
                .build();

        Page<Exam> examPage = new PageImpl<>(List.of(exam));

        TeacherMainPageAuthoredExamDTO authoredExamDTO = new TeacherMainPageAuthoredExamDTO(
                1L, 1L, "student1", "Authored Exam", "SUBMITTED"
        );

        when(examRepository.findByLectureTeacherSeqAndStatusNot(memberSeq, ExamStatus.CREATED, pageable))
                .thenReturn(examPage);
        when(teacherMainPageMapper.teacherMainPageAuthoredExamDTO(exam)).thenReturn(authoredExamDTO);

        // When
        Page<TeacherMainPageAuthoredExamDTO> result = teacherMainService.getAuthoredExamList(memberSeq, pageable);

        // Then
        assertEquals(1, result.getTotalElements());
        assertEquals(authoredExamDTO, result.getContent().get(0));
    }
}
