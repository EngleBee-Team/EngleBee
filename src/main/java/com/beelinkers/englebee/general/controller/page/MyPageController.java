package com.beelinkers.englebee.general.controller.page;

import com.beelinkers.englebee.general.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/my")
public class MyPageController {

  private final MyPageService myPageService;

  @GetMapping("/info")
  public String getMyInfoPage(/*@AuthenticationPrincipal UserDetails,*/Model model) {
     /*
      TODO : JWT Token 확인 이후, ROLE에 따라
       /my 에 들어온 요청을 각각 다른 페이지로 렌더링
    */
    return null;
  }

  @GetMapping("/account")
  public String getMyAccountPage(/*@AuthenticationPrincipal UserDetails,*/ Model model) {
     /*
      TODO : JWT Token 확인 이후, ROLE에 따라
       /my 에 들어온 요청을 각각 다른 페이지로 렌더링
    */
    return null;
  }
}
