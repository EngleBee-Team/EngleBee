package com.beelinkers.englebee.teacher.controller.page;

import com.beelinkers.englebee.auth.annotation.LoginMember;
import com.beelinkers.englebee.auth.oauth2.session.SessionMember;
import com.beelinkers.englebee.teacher.dto.response.TeacherExamFeedbackPageDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherExamRegisterPageDTO;
import com.beelinkers.englebee.teacher.service.TeacherExamPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TeacherExamPageController {

  private final TeacherExamPageService teacherExamPageService;

  @GetMapping("/exam/register/{examSeq}")
  public String getExamRegisterPage(@LoginMember SessionMember sessionMember,
      @PathVariable("examSeq") Long examSeq, Model model) {
    TeacherExamRegisterPageDTO teacherExamRegisterPageInfo = teacherExamPageService.getTeacherExamRegisterPageInfo(
        sessionMember.getSeq(), examSeq);
    model.addAttribute("examSeq", examSeq);
    model.addAttribute("studentSubjectLevels",
        teacherExamRegisterPageInfo.getStudentSubjectLevels());
    model.addAttribute("lecture6SubjectLevels",
        teacherExamRegisterPageInfo.getLectureSubjectLevels());
    return "teacher/exam-register";
  }

  @GetMapping("/exam/feedback/{examSeq}")
  public String getExamFeedbackPage(@LoginMember SessionMember sessionMember,
      @PathVariable("examSeq") Long examSeq, Model model) {
    TeacherExamFeedbackPageDTO teacherExamFeedbackPageDTO = teacherExamPageService.getTeacherExamFeedbackPageInfo(
        sessionMember.getSeq(), examSeq);
    model.addAttribute("examSeq", examSeq);
    model.addAttribute("examTitle", teacherExamFeedbackPageDTO.getExamTitle());
    model.addAttribute("teacherQuestions",
        teacherExamFeedbackPageDTO.getTeacherQuestionForTeacherToFeedbackDTOs());
    return "teacher/exam-feedback";
  }

}
