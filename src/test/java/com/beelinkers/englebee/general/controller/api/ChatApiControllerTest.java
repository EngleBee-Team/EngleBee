package com.beelinkers.englebee.general.controller.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import com.beelinkers.englebee.general.service.ChatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
public class ChatApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ChatService chatService;

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private ChatApiController chatApiController;

  @BeforeEach
  public void setUpSecurity(){
    mockMvc = MockMvcBuilders.webAppContextSetup(context)
        .apply(springSecurity())
        .build();
  }

  @Test
  @WithMockUser(roles = {"STUDENT"})
  @DisplayName("수업 종료 후 시험 생성 API 테스트")
  void 수업_종료_후_시험_생성_API_테스트(){
    //given
    Long lectureSeq = 1L;

    //when
    ResponseEntity<String> response = chatApiController.endLecture(lectureSeq);

    //then
    verify(chatService).finishLectureCreateExam(lectureSeq);
    assertEquals("수업이 종료되었고 시험 생성이 완료되었습니다", response.getBody());
    assertEquals(200, response.getStatusCodeValue());

  }

}
