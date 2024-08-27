package com.beelinkers.englebee.admin.controller.api;

import com.beelinkers.englebee.admin.dto.response.AdminDashboardMemberSubjectLevelCountDTO;
import com.beelinkers.englebee.admin.dto.response.AdminMemberSubjectLevelCountDTO;
import com.beelinkers.englebee.admin.service.AdminService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminApiController {

  private final AdminService adminService;

  @GetMapping("/userlevel")
  public ResponseEntity<AdminDashboardMemberSubjectLevelCountDTO> getChartUserTags() {
    log.info("getChartUserTags");
    List<AdminMemberSubjectLevelCountDTO> grammerList = adminService.getChartUserLevelGrammer();
    List<AdminMemberSubjectLevelCountDTO> sentenceList = adminService.getChartUserLevelSentence();
    List<AdminMemberSubjectLevelCountDTO> wordList = adminService.getChartUserLevelWord();
    AdminDashboardMemberSubjectLevelCountDTO memberSubjectLevelCountDTO = new AdminDashboardMemberSubjectLevelCountDTO(grammerList,sentenceList,wordList);
    return ResponseEntity.ok(memberSubjectLevelCountDTO);
  }

}
