package com.beelinkers.englebee.admin.controller.api;

import com.beelinkers.englebee.admin.dto.request.AdminYearMonthDTO;
import com.beelinkers.englebee.admin.dto.response.AdminDashboardDayGroupCountDTO;
import com.beelinkers.englebee.admin.dto.response.AdminDashboardMemberChartDTO;
import com.beelinkers.englebee.admin.dto.response.AdminMemberAgeGroupCountDTO;
import com.beelinkers.englebee.admin.dto.response.AdminMemberSubjectLevelCountDTO;
import com.beelinkers.englebee.admin.service.AdminService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminApiController {

  private final AdminService adminService;

  @GetMapping("/member-chart")
  public ResponseEntity<AdminDashboardMemberChartDTO> getChartUserTags() {
    List<AdminMemberSubjectLevelCountDTO> grammerList = adminService.getChartUserLevelGrammer();
    List<AdminMemberSubjectLevelCountDTO> sentenceList = adminService.getChartUserLevelSentence();
    List<AdminMemberSubjectLevelCountDTO> wordList = adminService.getChartUserLevelWord();
    List<AdminMemberAgeGroupCountDTO> ageList = adminService.getChartUserAgeGroup();
    AdminDashboardMemberChartDTO memberSubjectLevelCountDTO = new AdminDashboardMemberChartDTO(grammerList,sentenceList,wordList,ageList);
    return ResponseEntity.ok(memberSubjectLevelCountDTO);
  }

  @PostMapping("/call-llm")
  public ResponseEntity<List<AdminDashboardDayGroupCountDTO>> getChartCallLLM(@RequestBody AdminYearMonthDTO adminYearMonthDTO) {
    int year = Integer.parseInt(adminYearMonthDTO.getYear());
    int month = Integer.parseInt(adminYearMonthDTO.getMonth());
    List<AdminDashboardDayGroupCountDTO> llmList = adminService.getChartDayCallLLMCount(year,month);
    return ResponseEntity.ok(llmList);
  }

  @PostMapping("/create-lecture")
  public ResponseEntity<List<AdminDashboardDayGroupCountDTO>> getChartCreateLecture(@RequestBody AdminYearMonthDTO adminYearMonthDTO) {
    int year = Integer.parseInt(adminYearMonthDTO.getYear());
    int month = Integer.parseInt(adminYearMonthDTO.getMonth());
    List<AdminDashboardDayGroupCountDTO> lectureList = adminService.getChartDayCreateLectureCount(year,month);
    return ResponseEntity.ok(lectureList);
  }

}
