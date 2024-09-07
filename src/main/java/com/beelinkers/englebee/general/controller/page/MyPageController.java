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

      // Pagination 정보 생성 및 모델에 추가
      model.addAttribute("createdExamsPagination", paginationResponse(createdExamList));
      model.addAttribute("completedExamsPagination", paginationResponse(completedExamList));
      model.addAttribute("writtenQnaPagination", paginationResponse(writtenQnaList));
      model.addAttribute("writtenReplyPagination", paginationResponse(writtenReplyList));

      // 데이터 리스트 모델에 추가
      model.addAttribute("createdExams", createdExamList.getContent());
      model.addAttribute("completedExams", completedExamList.getContent());
      model.addAttribute("writtenQna", writtenQnaList.getContent());
      model.addAttribute("writtenReply", writtenReplyList.getContent());

      return "student/student-info";

    } else if (sessionUserRole.isTeacher()) {

      Page<TeacherMyPagePendingExamDTO> pendingExamList = teacherMyService.getTeacherMyPendingExamInfo(
          memberSeq, pageable
      );
      Page<TeacherMyPageExamHistoryDTO> examHistoryList = teacherMyService.getTeacherMyPageExamHistoryInfo(
          memberSeq, pageable
      );
      Page<TeacherMyPageWrittenQnaDTO> writtenQnaList = teacherMyService.getTeacherMyPageWrittenQnaInfo(
          memberSeq, pageable
      );
      Page<TeacherMyPageWrittenReplyDTO> writtenReplyList = teacherMyService.getTeacherMyPageWrittenReplyInfo(
          memberSeq, pageable
      );

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
