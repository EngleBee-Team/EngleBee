package com.beelinkers.englebee.admin.controller.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminPageController {

  @GetMapping("/dashboard")
  public String goToDashBoardPage() {
    return "admin/admin-dashboard";
  }
}
