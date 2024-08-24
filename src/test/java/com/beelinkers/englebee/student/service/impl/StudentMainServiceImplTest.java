package com.beelinkers.englebee.student.service.impl;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.general.domain.entity.*;
import com.beelinkers.englebee.general.domain.repository.ExamRepository;
import com.beelinkers.englebee.general.domain.repository.LectureRepository;
import com.beelinkers.englebee.general.domain.repository.QuestionRepository;
import com.beelinkers.englebee.student.dto.response.StudentMainPageLectureDTO;
import com.beelinkers.englebee.student.dto.response.StudentMainPageNewExamDTO;
import com.beelinkers.englebee.student.dto.response.StudentMainPageQuestionDTO;
import com.beelinkers.englebee.student.dto.response.StudentMainPageSubmitExamDTO;
import com.beelinkers.englebee.student.dto.response.mapper.StudentMainPageMapper;
import com.beelinkers.englebee.student.service.StudentMainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class StudentMainServiceImplTest {

    @Autowired
    private StudentMainService studentMainService;

    @MockBean
    private StudentMainPageMapper studentMainPageMapper;

    @MockBean
    private LectureRepository lectureRepository;

    @MockBean
    private QuestionRepository questionRepository;

    @MockBean
    private ExamRepository examRepository;

    private Pageable pageable;

    @BeforeEach
    void setUp() {
        pageable = PageRequest.of(0, 10);
    }

    @Test
    @DisplayName("수강하는 수업을 조회할 수 있다")
    void 수강하는_수업을_조회할_수_있다() {
        // given
        Member teacher = Member.builder().nickname("user3").build();
        Member student = Member.builder().nickname("user2").build();

        Lecture lecture = Lecture.builder()
                .teacher(teacher)
                .student(student)
                .title("기초 문법 강의")
                .build();

        Page<Lecture> lecturePage = new PageImpl<>(List.of(lecture));
        StudentMainPageLectureDTO lectureDTO = new StudentMainPageLectureDTO(
                1L, "user3", "기초 문법 강의", "CREATED", LocalDateTime.now(), List.of()
        );

        when(lectureRepository.findByStudentSeqAndStatus(1L, LectureStatus.CREATED, pageable)).thenReturn(lecturePage);
        when(studentMainPageMapper.mainPageLectureDto(lecture)).thenReturn(lectureDTO);

        // when
        Page<StudentMainPageLectureDTO> resultLecture = studentMainService.getLectureList(1L, pageable);

        // then
        assertThat(resultLecture.getTotalElements()).isEqualTo(1);
        assertThat(resultLecture.getContent().get(0).getTitle()).isEqualTo("기초 문법 강의");
        assertThat(resultLecture.getContent().get(0).getTeacherNickname()).isEqualTo("user3");
    }

    @Test
    @DisplayName("질문 목록을 조회할 수 있다")
    void 질문_목록을_조회할_수_있다(){
        // given
        Question question = Question.builder()
                .title("질문1")
                .build();
        Page<Question> questionPage = new PageImpl<>(List.of(question));
        StudentMainPageQuestionDTO questionDTO = new StudentMainPageQuestionDTO(
          1L, "user2", "질문1", LocalDateTime.now()
        );
        when(questionRepository.findAll(pageable)).thenReturn(questionPage);
        when(studentMainPageMapper.mainPageQuestionDTO(question)).thenReturn(questionDTO);

        // when
        Page<StudentMainPageQuestionDTO> resultQuestion = studentMainService.getQuestionList(pageable);

        // then
        assertThat(resultQuestion.getTotalElements()).isEqualTo(1);
        assertThat(resultQuestion.getContent().get(0).getTitle()).isEqualTo("질문1");
        assertThat(resultQuestion.getContent().get(0).getMemberNickname()).isEqualTo("user2");
        assertThat(resultQuestion.getContent().get(0).getCreatedAt()).isNotNull();
    }

    @Test
    @DisplayName("풀어야 할 시험 목록을 조회할 수 있다")
    void 풀어야_할_시험_목록을_조회할_수_있다() {
        // given
        Exam exam = Exam.builder()
                .lecture(Lecture.builder().teacher(Member.builder().nickname("user3").build()).build())
                .title("풀어야 할 시험1")
                .build();

        Page<Exam> examPage = new PageImpl<>(List.of(exam));
        StudentMainPageNewExamDTO examDTO = new StudentMainPageNewExamDTO(
                1L, 1L, "CREATED", "풀어야 할 시험1", "user3", LocalDateTime.now()
        );
        when(examRepository.findByLectureStudentSeqAndStatus(1L, ExamStatus.PREPARED, pageable)).thenReturn(examPage);
        when(studentMainPageMapper.mainPageNewExamDTO(exam)).thenReturn(examDTO);

        // when
        Page<StudentMainPageNewExamDTO> result = studentMainService.getNewExamList(1L, pageable);

        // then
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getTitle()).isEqualTo("풀어야 할 시험1");
        assertThat(result.getContent().get(0).getTeacherNickname()).isEqualTo("user3");
    }

    @Test
    @DisplayName("제출한 시험 목록을 조회할 수 있다")
    void 제출한_시험_목록을_조회할_수_있다() {
        // given
        Exam exam = Exam.builder()
                .lecture(Lecture.builder().teacher(Member.builder().nickname("user3").build()).build())
                .title("풀었던 시험1")
                .build();

        Page<Exam> examPage = new PageImpl<>(List.of(exam));
        StudentMainPageSubmitExamDTO examDTO = new StudentMainPageSubmitExamDTO(
                1L, 1L, "SUBMITTED", "풀었던 시험1", "user3", LocalDateTime.now()
        );
        when(examRepository.findByLectureStudentSeqAndStatusIn(1L, List.of(ExamStatus.SUBMITTED, ExamStatus.FEEDBACK_COMPLETED), pageable)).thenReturn(examPage);
        when(studentMainPageMapper.mainPageSubmitExamDTO(exam)).thenReturn(examDTO);

        // when
        Page<StudentMainPageSubmitExamDTO> result = studentMainService.getSubmitExamList(1L, pageable);

        // then
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getTitle()).isEqualTo("풀었던 시험1");
        assertThat(result.getContent().get(0).getTeacherNickname()).isEqualTo("user3");
    }
}
