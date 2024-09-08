package com.beelinkers.englebee.teacher.controller.page;

import com.beelinkers.englebee.auth.annotation.LoginMember;
import com.beelinkers.englebee.auth.oauth2.session.SessionMember;
import com.beelinkers.englebee.teacher.dto.response.TeacherExamFeedbackPageDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherExamRegisterPageDTO;
import com.beelinkers.englebee.teacher.service.TeacherExamPageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
  private final ObjectMapper objectMapper;

  @GetMapping("/exam/register/{examSeq}")
  public String getExamRegisterPage(@LoginMember SessionMember sessionMember,
      @PathVariable("examSeq") Long examSeq, Model model) throws JsonProcessingException {
    TeacherExamRegisterPageDTO teacherExamRegisterPageInfo = teacherExamPageService.getTeacherExamRegisterPageInfo(
        sessionMember.getSeq(), examSeq);
    model.addAttribute("examSeq", examSeq);
    model.addAttribute("studentGrade", teacherExamRegisterPageInfo.getStudentGrade());

    String studentSubjectLevelsJson = objectMapper.writeValueAsString(
        teacherExamRegisterPageInfo.getStudentSubjectLevels());
    model.addAttribute("studentSubjectLevels", studentSubjectLevelsJson);

    String lectureSubjectLevelsJson = objectMapper.writeValueAsString(
        teacherExamRegisterPageInfo.getLectureSubjectLevels());
    model.addAttribute("lectureSubjectLevels",
        lectureSubjectLevelsJson);

    return "teacher/exam-register";
  }

  @GetMapping("/exam/feedback/{examSeq}")
  public String getExamFeedbackPage(@LoginMember SessionMember sessionMember,
      @PathVariable("examSeq") Long examSeq, Model model) throws JsonProcessingException {
    TeacherExamFeedbackPageDTO teacherExamFeedbackPageDTO = teacherExamPageService.getTeacherExamFeedbackPageInfo(
        sessionMember.getSeq(), examSeq);

    String teacherQuestionsJson = objectMapper.writeValueAsString(
        teacherExamFeedbackPageDTO.getTeacherQuestionForTeacherToFeedbackDTOs());
    String lectureSubjectsJson = objectMapper.writeValueAsString(
        teacherExamFeedbackPageDTO.getLectureSubjects());

    model.addAttribute("examSeq", examSeq);
    model.addAttribute("studentGrade", teacherExamFeedbackPageDTO.getStudentGrade());
    model.addAttribute("examTitle", teacherExamFeedbackPageDTO.getExamTitle());
    model.addAttribute("teacherQuestions", teacherQuestionsJson);
    model.addAttribute("lectureSubjects", lectureSubjectsJson);

    return "teacher/exam-feedback";
  }

}
