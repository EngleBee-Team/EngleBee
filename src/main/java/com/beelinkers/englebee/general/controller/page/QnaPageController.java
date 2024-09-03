package com.beelinkers.englebee.general.controller.page;

import com.beelinkers.englebee.QuestionDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/qna")
public class QnaPageController {

  @GetMapping("/list")
  public String getQnaListPage(/*@AuthenticationPrincipal UserDetails,*/Model model) {
     /*
      TODO : Session 확인 이후, ROLE에 따라
       /my/info 에 들어온 요청을 각각 다른 페이지로 렌더링
    */
    List<QuestionDTO> list = new ArrayList<>();
    QuestionDTO dto = new QuestionDTO("I am a boy", "2024-08-23");
    list.add(dto);
    dto = new QuestionDTO("She is a girl", "2024-08-21");
    list.add(dto);
    model.addAttribute("MY_QA_LIST", list);
    return "qna/qna-list";
  }

  @GetMapping("/detail/{questionSeq}")
  public String getQnaDetailPage(/*@AuthenticationPrincipal UserDetails,*/Model model,
      @PathVariable String questionSeq) {
     /*
      TODO : Session 확인 이후, ROLE에 따라
       /my/info 에 들어온 요청을 각각 다른 페이지로 렌더링
    */
    return "qna/qna-detail";
  }

  @GetMapping("/register")
  public String getQnaRegisterPage(/*@AuthenticationPrincipal UserDetails,*/Model model) {
     /*
      TODO : Session 확인 이후, ROLE에 따라
       /my/info 에 들어온 요청을 각각 다른 페이지로 렌더링
    */
    return "qna/qna-register";
  }
}
