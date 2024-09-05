package com.beelinkers.englebee.general.controller.page;

import com.beelinkers.englebee.general.dto.response.QnaPageResponseDTO;
import com.beelinkers.englebee.general.service.QnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/qna")
public class QnaPageController {

  private final QnaService qnaService;

  @GetMapping("/list")
  public String getQnaListPage(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size, Model model) {

    PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "seq"));
    Page<QnaPageResponseDTO> qnaList = qnaService.getQnaListInfo(pageRequest);
    model.addAttribute("qnaList", qnaList);
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
