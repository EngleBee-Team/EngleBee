package com.beelinkers.englebee.admin.service;

import com.beelinkers.englebee.admin.dto.response.AdminDashboardDayGroupCountDTO;
import com.beelinkers.englebee.admin.dto.response.AdminMemberAgeGroupCountDTO;
import com.beelinkers.englebee.admin.dto.response.AdminMemberSubjectLevelCountDTO;
import java.util.List;

public interface AdminService {

  List<AdminMemberSubjectLevelCountDTO> getChartUserLevelGrammer();

  List<AdminMemberSubjectLevelCountDTO> getChartUserLevelSentence();

  List<AdminMemberSubjectLevelCountDTO> getChartUserLevelWord();

  List<AdminDashboardDayGroupCountDTO> getChartDayCallLLMCount(int year, int month);

  List<AdminMemberAgeGroupCountDTO> getChartUserAgeGroup();

  List<AdminDashboardDayGroupCountDTO> getChartDayCreateLectureCount(int year, int month);
}
