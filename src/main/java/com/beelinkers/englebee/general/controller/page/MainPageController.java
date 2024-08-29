package com.beelinkers.englebee.general.controller.page;

import com.beelinkers.englebee.auth.annotation.LoginMember;
import com.beelinkers.englebee.auth.domain.entity.Role;
import com.beelinkers.englebee.auth.oauth2.session.SessionMember;
import com.beelinkers.englebee.general.service.MainPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainPageController {

  private final MainPageService mainPageService;

  @GetMapping("/main")
  public String getMainPage(@LoginMember SessionMember sessionMember, Model model) {

    log.info("sessionMember = {}", sessionMember);
    if (sessionMember == null) {
      return "/common-main";
    }

    Long memberSeq = sessionMember.getSeq();
    model.addAttribute("nickname", mainPageService.getNickname(memberSeq));
    model.addAttribute("memberSeq", memberSeq);

    Role sessionUserRole = sessionMember.getRole();
    if (sessionUserRole.isStudent()) {
      return "student/student-main";
    } else if (sessionUserRole.isTeacher()) {
      return "teacher/teacher-main";
    }
    return "admin/admin-dashboard";
  }

}
