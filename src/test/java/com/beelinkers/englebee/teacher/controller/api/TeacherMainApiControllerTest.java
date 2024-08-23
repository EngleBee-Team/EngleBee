package com.beelinkers.englebee.teacher.controller.api;

import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageAuthoredExamDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageLectureDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageNewExamDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMainPageQuestionDTO;
import com.beelinkers.englebee.teacher.service.TeacherMainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TeacherMainApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherMainService teacherMainService;

    @BeforeEach
    void setUp() {
        // given
        Page<TeacherMainPageLectureDTO> lecturePage = new PageImpl<>(List.of(new TeacherMainPageLectureDTO(
            1L, "user3", "기초문법강의", "CREATED",
            LocalDateTime.now(), List.of(List.of("어법", "중"), List.of("문장", "중"))
        )));
        Page<TeacherMainPageQuestionDTO> questionPage = new PageImpl<>(List.of(new TeacherMainPageQuestionDTO(
            1L, "user2", "REST API 모범 사례", LocalDateTime.now()
        )));
        Page<TeacherMainPageNewExamDTO> newExamPage = new PageImpl<>(List.of(new TeacherMainPageNewExamDTO(
            1L, 1L, "user2", "new 시험", "PREPARED"
        )));
        Page<TeacherMainPageAuthoredExamDTO> quthoredExamPage = new PageImpl<>(List.of(new TeacherMainPageAuthoredExamDTO(
            1L, 1L, "user2", "new 시험", "FEEDBACK_COMPLETED"
        )));

        Mockito.when(teacherMainService.getLectureList(anyLong(), any(Pageable.class)))
                .thenReturn(lecturePage);
        Mockito.when(teacherMainService.getQuestionList(any(Pageable.class)))
                .thenReturn(questionPage);
        Mockito.when(teacherMainService.getNewExamList(anyLong(), any(Pageable.class)))
                .thenReturn(newExamPage);
        Mockito.when(teacherMainService.getAuthoredExamList(anyLong(), any(Pageable.class)))
                .thenReturn(quthoredExamPage);
    }

    @Test
    @DisplayName("강의 목록 조회 API 테스트")
    void 강의_목록_조회_API_테스트() throws Exception {
        //when
        mockMvc.perform(get("/api/teacher/main/lecture")
            .param("memberSeq", "3")
            .param("page", "0")
            .param("size", "10")
            .contentType(MediaType.APPLICATION_JSON))
            //then
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content").isArray())
            .andExpect(jsonPath("$.content[0].title").value("기초문법강의"))
            .andExpect(jsonPath("$.content[0].status").value("CREATED"))
            .andExpect(jsonPath("$.content[0].createdAt").exists())
            .andExpect(jsonPath("$.content[0].subjectLevelCode[0][0]").value("어법"))
            .andExpect(jsonPath("$.content[0].subjectLevelCode[0][1]").value("중"))
            .andExpect(jsonPath("$.pagination.totalPages").value(1))
            .andExpect(jsonPath("$.pagination.totalElements").value(1))
            .andExpect(jsonPath("$.pagination.currentPage").value(1));
    }

    @Test
    @DisplayName("질문 목록 조회 API 테스트")
    void 질문_목록_조회_API_테스트() throws Exception {
        //when
        mockMvc.perform(get("/api/teacher/main/question")
            .param("page", "0")
            .param("size", "10")
            .contentType(MediaType.APPLICATION_JSON))
            //then
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content").isArray())
            .andExpect(jsonPath("$.content[0].memberNickname").value("user2"))
            .andExpect(jsonPath("$.content[0].title").value("REST API 모범 사례"))
            .andExpect(jsonPath("$.content[0].createdAt").exists())
            .andExpect(jsonPath("$.pagination.totalPages").value(1))
            .andExpect(jsonPath("$.pagination.totalElements").value(1))
            .andExpect(jsonPath("$.pagination.currentPage").value(1));
    }

    @Test
    @DisplayName("출제 할 문제 목록 API 테스트")
    void 출제_할_문제_목록_API_테스트() throws Exception {
        //when
        mockMvc.perform(get("/api/teacher/main/new-exam")
            .param("memberSeq", "3")
            .param("page", "0")
            .param("size", "10")
            .contentType(MediaType.APPLICATION_JSON))
            //then
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content").isArray())
            .andExpect(jsonPath("$.content[0].studentNickname").value("user2"))
            .andExpect(jsonPath("$.content[0].title").value("new 시험"))
            .andExpect(jsonPath("$.content[0].status").value("PREPARED"))
            .andExpect(jsonPath("$.pagination.totalPages").value(1))
            .andExpect(jsonPath("$.pagination.totalElements").value(1))
            .andExpect(jsonPath("$.pagination.currentPage").value(1));
    }

    @Test
    @DisplayName("출제 한 목록 조회 API 테스트")
    void 출제_한_목록_조회_API_테스트() throws Exception {
        //when
        mockMvc.perform(get("/api/teacher/main/authored-exam")
            .param("memberSeq", "3")
            .param("page", "0")
            .param("size", "10")
            .contentType(MediaType.APPLICATION_JSON))
            //then
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content").isArray())
            .andExpect(jsonPath("$.content[0].studentNickname").value("user2"))
            .andExpect(jsonPath("$.content[0].title").value("new 시험"))
            .andExpect(jsonPath("$.content[0].status").value("FEEDBACK_COMPLETED"))
            .andExpect(jsonPath("$.pagination.totalPages").value(1))
            .andExpect(jsonPath("$.pagination.totalElements").value(1))
            .andExpect(jsonPath("$.pagination.currentPage").value(1));
    }
}
