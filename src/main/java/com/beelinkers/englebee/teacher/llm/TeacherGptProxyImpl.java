package com.beelinkers.englebee.teacher.llm;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import io.reactivex.Flowable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@Component
@RequiredArgsConstructor
public class TeacherGptProxyImpl implements TeacherGptProxy {

  private final OpenAiService openAiService;
  private final ObjectMapper objectMapper;

  @Override
  @Async("gptThreadPoolTask")
  public void processTeacherQuestionRecommendation(ChatCompletionRequest request,
      SseEmitter emitter) {
    try {
      log.info("Processing teacher question recommendation asynchronously on thread: "
          + Thread.currentThread().getName());
      Flowable<ChatCompletionChunk> flowable = openAiService.streamChatCompletion(request);
      flowable.blockingForEach(completion -> {
        try {
          String responseJson = objectMapper.writeValueAsString(completion);
          JsonNode choicesNode = objectMapper.readTree(responseJson).get("choices");
          extractAndSendContentForTeacherQuestionRecommendation(choicesNode, emitter);
        } catch (Exception e) {
          log.error("Error processing response: ", e);
          emitter.completeWithError(e);
        }
      });
      sendContentAsMessage("[englebee-finished]", emitter);
      emitter.complete();
    } catch (Exception e) {
      log.error("Error in stream completion: ", e);
      emitter.completeWithError(e);
    }
  }

  private void extractAndSendContentForTeacherQuestionRecommendation(JsonNode choicesNode,
      SseEmitter emitter) {
    choicesNode.forEach(choiceNode -> {
      JsonNode messageNode = choiceNode.get("message");
      if (messageNode != null && messageNode.has("content")) {
        JsonNode contentNode = messageNode.get("content");
        if (contentNode != null && !contentNode.isNull() && !contentNode.asText().isEmpty()) {
          String content = contentNode.asText();
          sendContentAsMessage(content, emitter);
        }
      }
    });
  }

  private void sendContentAsMessage(String content, SseEmitter emitter) {
    try {
      log.info("content = {}", content);
      String modifiedContent = content.replace(" ", "__SPACE__");
      emitter.send(SseEmitter.event()
          .name("message")
          .data(modifiedContent, MediaType.TEXT_EVENT_STREAM));
    } catch (Exception e) {
      log.error("Error sending message: ", e);
      emitter.completeWithError(e);
    }
  }
}
