package com.beelinkers.englebee.general.controller.page;

import com.beelinkers.englebee.auth.annotation.LoginMember;
import com.beelinkers.englebee.auth.oauth2.session.SessionMember;
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
  public String getQnaDetailPage(@LoginMember SessionMember sessionMember, Model model,
      @PathVariable Long questionSeq) {
    Long memberSeq = sessionMember.getSeq();
    try {
      QnaDetailPageResponseDTO qnaDetail = qnaService.getQnaDetailInfo(questionSeq);
      List<ReplyResponseDTO> replyList = qnaDetail.getReplies();

      log.info("가져온 댓글 목록: {}", replyList.size());

      model.addAttribute("memberSeq", memberSeq);
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

  @GetMapping("/register")
  public String getQnaRegisterPage(@LoginMember SessionMember sessionMember, Model model) {
    Long memberSeq = sessionMember.getSeq();
    model.addAttribute("memberSeq", memberSeq);
    return "qna/qna-register";
  }
}
