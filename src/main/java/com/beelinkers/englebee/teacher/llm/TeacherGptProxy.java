package com.beelinkers.englebee.teacher.llm;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface TeacherGptProxy {

  void processTeacherQuestionRecommendation(ChatCompletionRequest request, SseEmitter emitter);
}
