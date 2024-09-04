package com.beelinkers.englebee.general.controller.page;

import com.beelinkers.englebee.auth.annotation.LoginMember;
import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.auth.domain.entity.Role;
import com.beelinkers.englebee.auth.oauth2.session.SessionMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class ChatController {
  @GetMapping("/chat/{lectureseq}")
  public String goToSocketPage(@PathVariable("lectureseq") Long lectureSeq,@LoginMember SessionMember sessionMember ,Model model) {
    model.addAttribute("member", sessionMember);
    model.addAttribute("lectureseq", lectureSeq);
    return "general/webrtc";
  }
}
// 시큐리티 적용 필요