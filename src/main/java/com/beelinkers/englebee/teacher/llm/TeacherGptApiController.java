package com.beelinkers.englebee.teacher.llm;

import static com.beelinkers.englebee.teacher.llm.TeacherGptUtil.getSystemPromptForFeedbackRecommendation;
import static com.beelinkers.englebee.teacher.llm.TeacherGptUtil.getSystemPromptForTeacherQuestionRecommendation;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher/gpt/recommendation")
public class TeacherGptApiController {

  private final TeacherGptService teacherGptService;
  private final TeacherGptProxy teacherGptProxy;

  @GetMapping("/teacher-question")
  public ResponseEntity<SseEmitter> recommendTeacherQuestion(
      @RequestParam String studentGrade,
      @RequestParam String subjectCode,
      @RequestParam String levelCode,
      @RequestParam String difficulty) {

    // 5분 타임아웃 설정
    SseEmitter emitter = new SseEmitter((long) (5 * 60 * 1000));
    GptChatCompletionRequest completionRequest = teacherGptService.createUserPromptForTeacherQuestionRecommendation(
        studentGrade, subjectCode, levelCode);
    ChatCompletionRequest chatCompletionRequest = GptChatCompletionRequest.of(
        getSystemPromptForTeacherQuestionRecommendation(difficulty), completionRequest);
    try {
      teacherGptProxy.processRecommendationRequestToGpt(chatCompletionRequest, emitter);
    } catch (Exception e) {
      log.error("Error in processing chat request: ", e);
      emitter.completeWithError(e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    return ResponseEntity.ok(emitter);
  }

  @GetMapping("/feedback")
  public ResponseEntity<SseEmitter> recommendFeedback(
      @RequestParam String studentGrade,
      @RequestParam String teacherQuestionSeqsStr,
      @RequestParam String angryIndex) {

    // 5분 타임아웃 설정
    SseEmitter emitter = new SseEmitter((long) (5 * 60 * 1000));
    List<Long> teacherQuestionSeqs = Arrays.stream(teacherQuestionSeqsStr.split("-"))
        .mapToLong(Long::parseLong)
        .boxed()
        .toList();
    GptChatCompletionRequest completionRequest = teacherGptService.createUserPromptForFeedbackRecommendation(
        studentGrade, teacherQuestionSeqs);
    ChatCompletionRequest chatCompletionRequest = GptChatCompletionRequest.of(
        getSystemPromptForFeedbackRecommendation(angryIndex), completionRequest);
    try {
      teacherGptProxy.processRecommendationRequestToGpt(chatCompletionRequest, emitter);
    } catch (Exception e) {
      log.error("Error in processing chat request: ", e);
      emitter.completeWithError(e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    return ResponseEntity.ok(emitter);
  }

}
