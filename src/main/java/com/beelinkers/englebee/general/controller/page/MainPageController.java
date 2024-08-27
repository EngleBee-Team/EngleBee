package com.beelinkers.englebee.general.controller.page;

import com.beelinkers.englebee.general.service.MainPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final MainPageService mainPageService;

    @GetMapping("/main")
    public String getMainPage(/*@AuthenticationPrincipal UserDetails*/
            @RequestParam("memberSeq") Long memberSeq, Model model) {
    /*
      TODO : JWT Token 확인 이후, ROLE에 따라
       /my 에 들어온 요청을 각각 다른 페이지로 렌더링
    */
        String userRole = mainPageService.getUserRole(memberSeq);

        model.addAttribute("memberSeq", memberSeq);
        model.addAttribute("nickname", mainPageService.getNickname(memberSeq));

        if (userRole.equals("ROLE_STUDENT")) {
            return "student/student-main";
        } else if (userRole.equals("ROLE_TEACHER")) {
            return "teacher/teacher-main";
        }
        return "redirect:/main";

    }

}
