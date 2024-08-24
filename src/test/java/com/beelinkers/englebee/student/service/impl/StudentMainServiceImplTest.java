package com.beelinkers.englebee.student.service.impl;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.general.domain.entity.Lecture;
import com.beelinkers.englebee.general.domain.entity.LectureStatus;
import com.beelinkers.englebee.general.domain.repository.LectureRepository;
import com.beelinkers.englebee.student.dto.response.StudentMainPageLectureDTO;
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

    private Pageable pageable;

    @BeforeEach
    void setUp() {
        pageable = PageRequest.of(0, 10);
    }

    @Test
    @DisplayName("수강하는 수업을 조회할 수 있다")
    void 수강하는_수업을_조회할_수_있다() {
        // Given
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

        // Mock 설정
        when(lectureRepository.findByStudentSeqAndStatus(1L, LectureStatus.CREATED, pageable)).thenReturn(lecturePage);
        when(studentMainPageMapper.mainPageLectureDto(lecture)).thenReturn(lectureDTO);

        // When
        Page<StudentMainPageLectureDTO> resultLecture = studentMainService.getLectureList(1L, pageable);

        // Then
        assertThat(resultLecture.getTotalElements()).isEqualTo(1);
        assertThat(resultLecture.getContent().get(0).getTitle()).isEqualTo("기초 문법 강의");
        assertThat(resultLecture.getContent().get(0).getTeacherNickname()).isEqualTo("user3");
    }

}
