package com.beelinkers.englebee.general.controller.page;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.auth.domain.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ChatController {
  @GetMapping("/chat")
  public String goToSocketPage(Model model) {
    Member tempMember = Member.builder()
        .nickname("teacher1")
        .role(Role.TEACHER)
        .build();
    model.addAttribute("member", tempMember);
    return "general/webrtc";
  }
}
