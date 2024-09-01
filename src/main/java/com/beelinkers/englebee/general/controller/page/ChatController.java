package com.beelinkers.englebee.general.controller.page;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.auth.domain.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class ChatController {
  @GetMapping("/chat/{lectureseq}")
  public String goToSocketPage(@PathVariable("lectureseq") Long lectureSeq,Model model) {
    Member tempMember = Member.builder()
        .nickname("teacher1")
        .role(Role.TEACHER)
        .build();
    log.info("lectureSeq:"+lectureSeq);
    model.addAttribute("member", tempMember);
    model.addAttribute("lectureseq", lectureSeq);
    return "general/webrtc";
  }
}
// 시큐리티 적용 필요