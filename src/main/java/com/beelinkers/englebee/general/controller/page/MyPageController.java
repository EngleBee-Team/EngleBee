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
import com.beelinkers.englebee.teacher.service.TeacherMyService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

  private final StudentMyService studentMyService;
  private final TeacherMyService teacherMyService;

  @GetMapping("/info")
  public String getMyInfoPage(@LoginMember SessionMember sessionMember, Model model,
      @PageableDefault(size = 10) Pageable pageable) {

    Long memberSeq = sessionMember.getSeq();
    model.addAttribute("nickname", myPageService.getNickname(memberSeq));
    model.addAttribute("memberSeq", memberSeq);

    Role sessionUserRole = sessionMember.getRole();
    if (sessionUserRole.isStudent()) {

      Page<StudentMyPageCreatedExamDTO> createdExamList = studentMyService.getStudentMyCreatedExamInfo(
          memberSeq, pageable);
      Page<StudentMyPageCompletedExamDTO> completedExamList = studentMyService.getStudentMyCompletedExamInfo(
          memberSeq, pageable);
      Page<StudentMyPageWrittenQnaDTO> writtenQnaList = studentMyService.getStudentMyWrittenQnaInfo(
          memberSeq, pageable);
      Page<StudentMyPageWrittenReplyDTO> writtenReplyList = studentMyService.getStudentMyWrittenReplyInfo(
          memberSeq, pageable);

      PaginationResponseDTO createdExamsPagination = new PaginationResponseDTO(
          createdExamList.getNumber(), createdExamList.getSize(), createdExamList.getTotalPages(),
          createdExamList.getTotalElements(), createdExamList.hasPrevious(),
          createdExamList.hasNext());

      PaginationResponseDTO completedExamsPagination = new PaginationResponseDTO(
          completedExamList.getNumber(), completedExamList.getSize(),
          completedExamList.getTotalPages(),
          completedExamList.getTotalElements(), completedExamList.hasPrevious(),
          completedExamList.hasNext());

      PaginationResponseDTO writtenQnaPagination = new PaginationResponseDTO(
          writtenQnaList.getNumber(), writtenQnaList.getSize(), writtenQnaList.getTotalPages(),
          writtenQnaList.getTotalElements(), writtenQnaList.hasPrevious(),
          writtenQnaList.hasNext());

      PaginationResponseDTO writtenReplyPagination = new PaginationResponseDTO(
          writtenReplyList.getNumber(), writtenReplyList.getSize(),
          writtenReplyList.getTotalPages(),
          writtenReplyList.getTotalElements(), writtenReplyList.hasPrevious(),
          writtenReplyList.hasNext());

      log.info(" 풀어야할 시험 : {} ", createdExamList);
      log.info(" 제출한 시험 : {} ", completedExamList);
      log.info(" 작성한 Q&A : {} ", writtenQnaList);
      log.info(" 작성한 댓글 : {} ", writtenReplyList);

      Map<String, Object> data = new HashMap<>();
      data.put("createdExams", createdExamList.getContent());
      data.put("completedExams", completedExamList.getContent());
      data.put("writtenQna", writtenQnaList.getContent());
      data.put("writtenReply", writtenReplyList.getContent());

      model.addAttribute("data", data);
      model.addAttribute("createdExamsPagination", createdExamsPagination);
      model.addAttribute("completedExamsPagination", completedExamsPagination);
      model.addAttribute("writtenQnaPagination", writtenQnaPagination);
      model.addAttribute("writtenReplyPagination", writtenReplyPagination);

      return "student/student-info";

    } else if (sessionUserRole.isTeacher()) {
      return "teacher/teacher-info";
    }
    return "/index";
  }

}
