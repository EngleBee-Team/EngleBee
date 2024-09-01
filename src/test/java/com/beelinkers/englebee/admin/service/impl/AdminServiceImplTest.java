package com.beelinkers.englebee.admin.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.beelinkers.englebee.admin.domain.repository.CallLLMLogRepository;
import com.beelinkers.englebee.admin.domain.repository.CreatedLectureLogRepository;
import com.beelinkers.englebee.admin.dto.response.AdminDashboardDayGroupCountDTO;
import com.beelinkers.englebee.admin.dto.response.AdminMemberAgeGroupCountDTO;
import com.beelinkers.englebee.admin.dto.response.AdminMemberSubjectLevelCountDTO;
import com.beelinkers.englebee.auth.domain.entity.StudentGrade;
import com.beelinkers.englebee.auth.domain.repository.MemberRepository;
import com.beelinkers.englebee.general.domain.entity.LevelCode;
import com.beelinkers.englebee.general.domain.repository.MemberSubjectLevelRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class AdminServiceImplTest {

  @Mock
  private MemberSubjectLevelRepository memberSubjectLevelRepository;

  @Mock
  private CallLLMLogRepository llmLogRepository;

  @Mock
  private MemberRepository memberRepository;

  @Mock
  private CreatedLectureLogRepository lectureLogRepository;

  @InjectMocks
  private AdminServiceImpl adminService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("멤버 과목 레벨별 그룹 차트 서비스 테스트")
  void 멤버_과목_레벨별_그룹_차트_API_테스트() {
    //given
    List<AdminMemberSubjectLevelCountDTO> expected = List.of(
        new AdminMemberSubjectLevelCountDTO(LevelCode.LOW,22L)
    );
    when(memberSubjectLevelRepository.findLevelCountsGrammer()).thenReturn(expected);

    //when
    List<AdminMemberSubjectLevelCountDTO> result = adminService.getChartUserLevelGrammer();

    //then
    assertEquals(expected, result);
  }

  @Test
  @DisplayName("LLM 호출 차트 조회 서비스 테스트")
  void LLM_호출_차트_조회_API_테스트(){
    //given
    int year = 2024;
    int month = 9;
    List<Object[]> queryResult = Arrays.asList(
        new Object[]{1, 100L},
        new Object[]{2, 200L}
    );
    List<AdminDashboardDayGroupCountDTO> expected = Arrays.asList(
        new AdminDashboardDayGroupCountDTO(1, 100L),
        new AdminDashboardDayGroupCountDTO(2, 200L)
    );

    when(llmLogRepository.findCallLLMGroupByDay(year,month)).thenReturn(queryResult);

    //when
    List<AdminDashboardDayGroupCountDTO> result = adminService.getChartDayCallLLMCount(year,month);

    //then
    assertEquals(expected.get(0).getCount(), result.get(0).getCount());
  }

  @Test
  @DisplayName("회원 학년별 그룹 차트 서비스 테스트")
  void 회원_학년별_그룹_차트_서비스_테스트(){
    //given
    List<AdminMemberAgeGroupCountDTO> expected = List.of(new AdminMemberAgeGroupCountDTO(
        StudentGrade.TENTH_GRADE,20L));
    when(memberRepository.findAgeGroupCount()).thenReturn(expected);

    //when
    List<AdminMemberAgeGroupCountDTO> result = adminService.getChartUserAgeGroup();

    //then
    assertEquals(expected, result);
  }

  @Test
  @DisplayName("강의 생성 차트 조회 서비스 테스트")
  void 강의_생성_차트_조회_서비스_테스트(){
    //given
    int year = 2024;
    int month = 9;
    List<Object[]> queryResult = Arrays.asList(
        new Object[]{1,50L},
        new Object[]{2,100L}
    );
    List<AdminDashboardDayGroupCountDTO> expected = Arrays.asList(
        new AdminDashboardDayGroupCountDTO(1,50L),
        new AdminDashboardDayGroupCountDTO(2,100L)
    );
    when(lectureLogRepository.findCreateLectureGroupByDay(year,month)).thenReturn(queryResult);

    //when
    List<AdminDashboardDayGroupCountDTO> result = adminService.getChartDayCreateLectureCount(year,month);

    //then
    assertEquals(expected.get(0).getCount(), result.get(0).getCount());
  }
}