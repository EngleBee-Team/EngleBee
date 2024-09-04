package com.beelinkers.englebee.student.controller.page;

import com.beelinkers.englebee.auth.annotation.LoginMember;
import com.beelinkers.englebee.auth.oauth2.session.SessionMember;
import com.beelinkers.englebee.student.dto.response.StudentExamSolvePageDTO;
import com.beelinkers.englebee.student.service.StudentExamPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StudentExamPageController {

  private final StudentExamPageService studentExamPageService;

  @GetMapping("/exam/solve/{examSeq}")
  public String getExamSolvePage(@LoginMember SessionMember sessionMember,
      @PathVariable("examSeq") Long examSeq, Model model) {
    Long studentSeq = sessionMember.getSeq();
    StudentExamSolvePageDTO studentExamSolvePageDTO = studentExamPageService.getStudentExamSolvePageDTO(
        studentSeq, examSeq);
    model.addAttribute("examSeq", examSeq);
    model.addAttribute("examTitle", studentExamSolvePageDTO.getExamTitle());
    model.addAttribute("teacherQuestions",
        studentExamSolvePageDTO.getTeacherQuestionForStudentToSolveDTOs());
    return "student/exam-solve";
  }

}
