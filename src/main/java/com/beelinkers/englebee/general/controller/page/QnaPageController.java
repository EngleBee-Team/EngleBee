package com.beelinkers.englebee.general.controller.page;

import com.beelinkers.englebee.general.dto.request.ReplyRequestDTO;
import com.beelinkers.englebee.general.dto.response.QnaDetailPageResponseDTO;
import com.beelinkers.englebee.general.dto.response.QnaPageResponseDTO;
import com.beelinkers.englebee.general.dto.response.ReplyResponseDTO;
import com.beelinkers.englebee.general.service.QnaService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
  public String getQnaDetailPage(Model model, @PathVariable Long questionSeq) {
    try {
      QnaDetailPageResponseDTO qnaDetail = qnaService.getQnaDetailInfo(questionSeq);
      List<ReplyResponseDTO> replyList = qnaDetail.getReplies();

      model.addAttribute("qnaDetail", qnaDetail);
      model.addAttribute("replyList", replyList);
      model.addAttribute("newReply", new ReplyRequestDTO());
      return "qna/qna-detail";
    } catch (EntityNotFoundException e) {
      model.addAttribute("error", "해당 질문을 찾을 수 없습니다.");
      return "error/404";
    } catch (Exception e) {
      model.addAttribute("error", "게시글을 불러오는 중 문제가 발생했습니다.");
      return "error/500";
    }
  }

  // reply insert
  @PostMapping("/reply")
  public String registerReply(@ModelAttribute("newReply") ReplyRequestDTO replyRequestDTO,
      @RequestParam("questionSeq") Long questionSeq,
      RedirectAttributes redirectAttributes) {
    try {
      qnaService.registerReplyInfo(replyRequestDTO);

      // 댓글 등록 후 성공 메시지 전달
      redirectAttributes.addFlashAttribute("successMessage", "댓글이 성공적으로 등록되었습니다.");

      // 다시 상세 페이지로 리다이렉트
      return "redirect:/qna/detail/" + questionSeq;
    } catch (Exception e) {
      // 에러 발생 시 에러 메시지 전달
      redirectAttributes.addFlashAttribute("errorMessage", "댓글 등록 중 오류가 발생했습니다.");
      return "redirect:/qna/detail/" + questionSeq;
    }
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
