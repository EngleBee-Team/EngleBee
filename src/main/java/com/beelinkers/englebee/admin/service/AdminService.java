package com.beelinkers.englebee.admin.service;

import com.beelinkers.englebee.admin.dto.response.AdminMemberSubjectLevelCountDTO;
import java.util.List;

public interface AdminService {

  List<AdminMemberSubjectLevelCountDTO> getChartUserLevelGrammer();

  List<AdminMemberSubjectLevelCountDTO> getChartUserLevelSentence();

  List<AdminMemberSubjectLevelCountDTO> getChartUserLevelWord();
}
