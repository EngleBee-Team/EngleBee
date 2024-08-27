package com.beelinkers.englebee.admin.service.impl;

import com.beelinkers.englebee.admin.dto.response.AdminMemberSubjectLevelCountDTO;
import com.beelinkers.englebee.admin.service.AdminService;
import com.beelinkers.englebee.general.domain.repository.MemberSubjectLevelRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

  private final MemberSubjectLevelRepository memberSubjectLevelRepository;

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


}
