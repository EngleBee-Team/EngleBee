package com.beelinkers.englebee.admin.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDashboardMemberSubjectLevelCountDTO {
  private List<AdminMemberSubjectLevelCountDTO> grammarList;
  private List<AdminMemberSubjectLevelCountDTO> sentenceList;
  private List<AdminMemberSubjectLevelCountDTO> wordList;
}
