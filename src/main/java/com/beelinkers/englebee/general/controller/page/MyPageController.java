package com.beelinkers.englebee.general.controller.page;

import com.beelinkers.englebee.auth.annotation.LoginMember;
import com.beelinkers.englebee.auth.domain.entity.Role;
import com.beelinkers.englebee.auth.oauth2.session.SessionMember;
import com.beelinkers.englebee.general.dto.response.PaginationResponseDTO;
import com.beelinkers.englebee.general.service.MyPageService;
import com.beelinkers.englebee.student.dto.response.StudentMyPageCompletedExamDTO;
import com.beelinkers.englebee.student.dto.response.StudentMyPageCreatedExamDTO;
import com.beelinkers.englebee.student.dto.response.StudentMyPageWrittenQnaDTO;
import com.beelinkers.englebee.student.dto.response.StudentMyPageWrittenReplyDTO;
import com.beelinkers.englebee.student.service.StudentMyService;
import com.beelinkers.englebee.teacher.dto.response.TeacherMyPageExamHistoryDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMyPagePendingExamDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMyPageWrittenQnaDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMyPageWrittenReplyDTO;
import com.beelinkers.englebee.teacher.service.TeacherMyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/my")
public class MyPageController {

  private final MyPageService myPageService;

  private final StudentMyService studentMyService;
  private final TeacherMyService teacherMyService;

  @GetMapping("/info")
  public String getMyInfoPage(
      @LoginMember SessionMember sessionMember,
      Model model,
      @PageableDefault(size = 10) Pageable pageable,
      @RequestParam(value = "pagePending", defaultValue = "0") int pagePending,
      @RequestParam(value = "pageCreated", defaultValue = "0") int pageCreated,
      @RequestParam(value = "pageQna", defaultValue = "0") int pageQna,
      @RequestParam(value = "pageReply", defaultValue = "0") int pageReply) {

    Long memberSeq = sessionMember.getSeq();
    model.addAttribute("nickname", myPageService.getNickname(memberSeq));
    model.addAttribute("memberSeq", memberSeq);

    Role sessionUserRole = sessionMember.getRole();
    if (sessionUserRole.isStudent()) {

      Pageable pendingPageable = PageRequest.of(pagePending, pageable.getPageSize());
      Pageable createdPageable = PageRequest.of(pageCreated, pageable.getPageSize());
      Pageable qnaPageable = PageRequest.of(pageQna, pageable.getPageSize());
      Pageable replyPageable = PageRequest.of(pageReply, pageable.getPageSize());

      Page<StudentMyPageCreatedExamDTO> createdExamList = studentMyService.getStudentMyCreatedExamInfo(
          memberSeq, createdPageable);
      Page<StudentMyPageCompletedExamDTO> completedExamList = studentMyService.getStudentMyCompletedExamInfo(
          memberSeq, pendingPageable);
      Page<StudentMyPageWrittenQnaDTO> writtenQnaList = studentMyService.getStudentMyWrittenQnaInfo(
          memberSeq, qnaPageable);
      Page<StudentMyPageWrittenReplyDTO> writtenReplyList = studentMyService.getStudentMyWrittenReplyInfo(
          memberSeq, replyPageable);

      model.addAttribute("createdExamsPagination", paginationResponse(createdExamList));
      model.addAttribute("completedExamsPagination", paginationResponse(completedExamList));
      model.addAttribute("writtenQnaPagination", paginationResponse(writtenQnaList));
      model.addAttribute("writtenReplyPagination", paginationResponse(writtenReplyList));

      model.addAttribute("createdExams", createdExamList.getContent());
      model.addAttribute("completedExams", completedExamList.getContent());
      model.addAttribute("writtenQna", writtenQnaList.getContent());
      model.addAttribute("writtenReply", writtenReplyList.getContent());

      return "student/student-info";

    } else if (sessionUserRole.isTeacher()) {

      Pageable pendingPageable = PageRequest.of(pagePending, pageable.getPageSize());
      Pageable createdPageable = PageRequest.of(pageCreated, pageable.getPageSize());
      Pageable qnaPageable = PageRequest.of(pageQna, pageable.getPageSize());
      Pageable replyPageable = PageRequest.of(pageReply, pageable.getPageSize());

      Page<TeacherMyPagePendingExamDTO> pendingExamList = teacherMyService.getTeacherMyPendingExamInfo(
          memberSeq, pendingPageable);
      Page<TeacherMyPageExamHistoryDTO> examHistoryList = teacherMyService.getTeacherMyPageExamHistoryInfo(
          memberSeq, createdPageable);
      Page<TeacherMyPageWrittenQnaDTO> writtenQnaList = teacherMyService.getTeacherMyPageWrittenQnaInfo(
          memberSeq, qnaPageable);
      Page<TeacherMyPageWrittenReplyDTO> writtenReplyList = teacherMyService.getTeacherMyPageWrittenReplyInfo(
          memberSeq, replyPageable);

      model.addAttribute("pendingExamPagination", paginationResponse(pendingExamList));
      model.addAttribute("examHistoryPagination", paginationResponse(examHistoryList));
      model.addAttribute("writtenQnaPagination", paginationResponse(writtenQnaList));
      model.addAttribute("writtenReplyPagination", paginationResponse(writtenReplyList));

      model.addAttribute("pendingExam", pendingExamList.getContent());
      model.addAttribute("examHistory", examHistoryList.getContent());
      model.addAttribute("writtenQna", writtenQnaList.getContent());
      model.addAttribute("writtenReply", writtenReplyList.getContent());

      return "teacher/teacher-info";
    }
    return "/index";
  }

  private PaginationResponseDTO paginationResponse(Page<?> page) {
    return new PaginationResponseDTO(
        page.getNumber(), page.getSize(), page.getTotalPages(),
        page.getTotalElements(), page.hasPrevious(), page.hasNext());
  }


}
