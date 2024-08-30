package com.beelinkers.englebee.admin.service.impl;

import com.beelinkers.englebee.admin.domain.repository.CallLLMLogRepository;
import com.beelinkers.englebee.admin.domain.repository.CreatedLectureLogRepository;
import com.beelinkers.englebee.admin.dto.response.AdminDashboardDayGroupCountDTO;
import com.beelinkers.englebee.admin.dto.response.AdminMemberAgeGroupCountDTO;
import com.beelinkers.englebee.admin.dto.response.AdminMemberSubjectLevelCountDTO;
import com.beelinkers.englebee.admin.service.AdminService;
import com.beelinkers.englebee.auth.domain.repository.MemberRepository;
import com.beelinkers.englebee.general.domain.repository.MemberSubjectLevelRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

  private final MemberSubjectLevelRepository memberSubjectLevelRepository;
  private final CallLLMLogRepository callLLMLogRepository;
  private final MemberRepository memberRepository;
  private final CreatedLectureLogRepository createdLectureLogRepository;

  @Override
  public List<AdminMemberSubjectLevelCountDTO> getChartUserLevelGrammer() {
    return memberSubjectLevelRepository.findLevelCountsGrammer();
  }

  @Override
  public List<AdminMemberSubjectLevelCountDTO> getChartUserLevelSentence() {
    return memberSubjectLevelRepository.findLevelCountsSentence();
  }

  @Override
  public List<AdminMemberSubjectLevelCountDTO> getChartUserLevelWord() {
    return memberSubjectLevelRepository.findLevelCountsWord();
  }

  @Override
  public List<AdminDashboardDayGroupCountDTO> getChartDayCallLLMCount(int year,
      int month) {
    List<Object[]> results = callLLMLogRepository.findCallLLMGroupByDay(year, month);
    return results.stream()
        .map(result -> new AdminDashboardDayGroupCountDTO(((Number) result[0]).intValue(), ((Number) result[1]).longValue()))
        .collect(Collectors.toList());
  }

  @Override
  public List<AdminMemberAgeGroupCountDTO> getChartUserAgeGroup() {
    List<AdminMemberAgeGroupCountDTO> ageGroupList = memberRepository.findAgeGroupCount();
    return ageGroupList;
  }

  @Override
  public List<AdminDashboardDayGroupCountDTO> getChartDayCreateLectureCount(int year, int month) {
    List<Object[]> results = createdLectureLogRepository.findCreateLectureGroupByDay(year, month);
    return results.stream()
        .map(result -> new AdminDashboardDayGroupCountDTO(((Number) result[0]).intValue(), ((Number) result[1]).longValue()))
        .collect(Collectors.toList());
  }

}
