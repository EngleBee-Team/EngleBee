package com.beelinkers.englebee.auth.controller.page;

import com.beelinkers.englebee.auth.annotation.SignupProgressMember;
import com.beelinkers.englebee.auth.oauth2.session.SignupProgressSessionMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class SignupProgressPageController {

  @GetMapping("/signup")
  public String getSignupProgressPage(
      @SignupProgressMember SignupProgressSessionMember signupProgressSessionMember) {
    if (signupProgressSessionMember == null) {
      return "index";
    }
    return "auth/signup-progress";
  }

}
