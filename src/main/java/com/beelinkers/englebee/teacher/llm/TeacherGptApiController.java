package com.beelinkers.englebee.teacher.llm;

import static com.beelinkers.englebee.teacher.llm.TeacherGptUtil.getSystemPromptForTeacherQuestionRecommendation;

import com.beelinkers.englebee.teacher.dto.response.TeacherExamRegisterPageDTO;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher/gpt")
public class TeacherGptApiController {

  private final TeacherGptService teacherGptService;
  private final TeacherGptProxy teacherGptProxy;

  @GetMapping("/teacher-question/recommendation")
  public ResponseEntity<SseEmitter> recommendTeacherQuestion(
      @ModelAttribute TeacherExamRegisterPageDTO teacherExamRegisterPageDTO) {

    // 5분 타임아웃 설정
    SseEmitter emitter = new SseEmitter((long) (5 * 60 * 1000));
    // 유저 메시지 생성
    GptChatCompletionRequest completionRequest = teacherGptService.createUserPromptForTeacherQuestionRecommendation(
        teacherExamRegisterPageDTO);
    // 시스템 프롬프트 + 유저 메시지를 담은 GPT 프롬프트 생성
    ChatCompletionRequest chatCompletionRequest = GptChatCompletionRequest.of(
        getSystemPromptForTeacherQuestionRecommendation(), completionRequest);
    try {
      teacherGptProxy.processTeacherQuestionRecommendation(chatCompletionRequest, emitter);
    } catch (Exception e) {
      log.error("Error in processing chat request: ", e);
      emitter.completeWithError(e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    return ResponseEntity.ok(emitter);
  }

}
