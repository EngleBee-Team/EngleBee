package com.beelinkers.englebee.admin.controller.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import com.beelinkers.englebee.admin.dto.request.AdminYearMonthDTO;
import com.beelinkers.englebee.admin.dto.response.AdminDashboardDayGroupCountDTO;
import com.beelinkers.englebee.admin.dto.response.AdminDashboardMemberChartDTO;
import com.beelinkers.englebee.admin.dto.response.AdminMemberAgeGroupCountDTO;
import com.beelinkers.englebee.admin.dto.response.AdminMemberSubjectLevelCountDTO;
import com.beelinkers.englebee.admin.service.AdminService;
import com.beelinkers.englebee.auth.domain.entity.StudentGrade;
import com.beelinkers.englebee.general.domain.entity.LevelCode;
import java.util.List;
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
public class AdminApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AdminService adminService;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private AdminApiController adminApiController;

  @BeforeEach
  public void setupSecurity(){
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .apply(springSecurity())
        .build();
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  @DisplayName("회원 차트 조회 API 테스트")
  void 회원_차트_조회_API_테스트() throws Exception {

    //given
    List<AdminMemberSubjectLevelCountDTO> grammerList = List.of(new AdminMemberSubjectLevelCountDTO(
        LevelCode.LOW,22L));
    List<AdminMemberSubjectLevelCountDTO> sentenceList = List.of(new AdminMemberSubjectLevelCountDTO(
        LevelCode.MIDDLE,23L));
    List<AdminMemberSubjectLevelCountDTO> wordList = List.of(new AdminMemberSubjectLevelCountDTO(
        LevelCode.HIGH,9L));
    List<AdminMemberAgeGroupCountDTO> ageList = List.of(new AdminMemberAgeGroupCountDTO(
        StudentGrade.NINTH_GRADE,2L));

    AdminDashboardMemberChartDTO expectedResponse = new AdminDashboardMemberChartDTO(grammerList,sentenceList,wordList,ageList);
    when(adminService.getChartUserLevelGrammer()).thenReturn(grammerList);
    when(adminService.getChartUserLevelSentence()).thenReturn(sentenceList);
    when(adminService.getChartUserLevelWord()).thenReturn(wordList);
    when(adminService.getChartUserAgeGroup()).thenReturn(ageList);

    //when
    ResponseEntity<AdminDashboardMemberChartDTO> response = adminApiController.getChartUserTags();

    //then
    assertEquals(expectedResponse.getGrammarList(), response.getBody().getGrammarList());
    assertEquals(expectedResponse.getSentenceList(), response.getBody().getSentenceList());
    assertEquals(200, response.getStatusCodeValue());
  }

  @Test
  @WithMockUser(roles={"ADMIN"})
  @DisplayName("LLM 호출 차트 조회 API 테스트")
  void LLM_호출_차트_조회_테스트() throws Exception{
    //given
    AdminYearMonthDTO requestDTO = new AdminYearMonthDTO("2024","08");
    int year = 2024;
    int month = 8;
    List<AdminDashboardDayGroupCountDTO> llmList = List.of(new AdminDashboardDayGroupCountDTO(1,100L));
    when(adminService.getChartDayCallLLMCount(year,month)).thenReturn(llmList);

    //when
    ResponseEntity<List<AdminDashboardDayGroupCountDTO>> response = adminApiController.getChartCallLLM(requestDTO);

    //then
    assertEquals(llmList,response.getBody());
    assertEquals(200,response.getStatusCodeValue());
  }

  @Test
  @WithMockUser(roles={"ADMIN"})
  @DisplayName("강의 생성 차트 조회 API 테스트")
  void 강의_생성_차트_조회_API_테스트() throws Exception{
    //given
    AdminYearMonthDTO requestDTO = new AdminYearMonthDTO("2024","08");
    int year = 2024;
    int month = 8;
    List<AdminDashboardDayGroupCountDTO> lectureList = List.of(new AdminDashboardDayGroupCountDTO(2,50L));
    when(adminService.getChartDayCreateLectureCount(year,month)).thenReturn(lectureList);

    //when
    ResponseEntity<List<AdminDashboardDayGroupCountDTO>> response = adminApiController.getChartCreateLecture(requestDTO);

    //then
    assertEquals(lectureList.get(0).getCount(),response.getBody().get(0).getCount());
    assertEquals(200,response.getStatusCodeValue());
  }

}
