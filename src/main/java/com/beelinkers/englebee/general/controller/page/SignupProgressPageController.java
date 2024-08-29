package com.beelinkers.englebee.general.controller.page;

import com.beelinkers.englebee.auth.oauth2.session.SignupProgressSessionMember;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class SignupProgressPageController {

  @GetMapping("/signup")
  public String getSignupProgressPage(HttpSession httpSession) {
    SignupProgressSessionMember session = (SignupProgressSessionMember) httpSession.getAttribute(
        "SIGNUP_PROGRESS_SESSION_MEMBER");
    if (session == null) {
      return "/common-main";
    }
    log.info("signup progress session = {}", session);
    return "/auth/signup-progress";
  }

}
