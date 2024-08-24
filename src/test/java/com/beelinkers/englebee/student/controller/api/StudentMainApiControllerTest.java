package com.beelinkers.englebee.student.controller.api;

import com.beelinkers.englebee.student.dto.response.StudentMainPageLectureDTO;
import com.beelinkers.englebee.student.service.StudentMainService;
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
public class StudentMainApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentMainService studentMainService;

    @BeforeEach
    void setUp() {
        // given
        Page<StudentMainPageLectureDTO> lecturePage = new PageImpl<>(List.of(new StudentMainPageLectureDTO(
                1L, "teacher1", "기초문법강의", "CREATED",
                LocalDateTime.now(), List.of(List.of("어법", "중"), List.of("문장", "중"))
        )));

        Mockito.when(studentMainService.getLectureList(anyLong(), any(Pageable.class)))
                .thenReturn(lecturePage);
    }

    @Test
    @DisplayName("수강 중인 강의 목록 조회 API 테스트")
    void 수강_중인_강의_목록_조회_API_테스트() throws Exception {
        // when
        mockMvc.perform(get("/api/student/main/lecture")
            .param("memberSeq", "2")
            .param("page", "1")
            .param("size", "10")
            .contentType(MediaType.APPLICATION_JSON))
            // then
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


}
