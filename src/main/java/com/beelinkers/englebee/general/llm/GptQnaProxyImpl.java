package com.beelinkers.englebee.general.llm;

import static com.beelinkers.englebee.general.llm.GeneralGptUtil.getSystemPromptForTeacherQuestionRecommendation;

import com.beelinkers.englebee.teacher.llm.GptChatCompletionRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import io.reactivex.Flowable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GptQnaProxyImpl implements GptQnaProxy {

  private final OpenAiService openAiService;
  private final ObjectMapper objectMapper;

  @Override
  public String makeAutoReply(String questionContent) {
    ChatCompletionRequest requestToGpt = GptChatCompletionRequest.of(
        getSystemPromptForTeacherQuestionRecommendation(),
        new GptChatCompletionRequest(questionContent));
    StringBuilder sb = new StringBuilder();
    try {
      Flowable<ChatCompletionChunk> flowable = openAiService.streamChatCompletion(requestToGpt);
      flowable.blockingForEach(completion -> {
        try {
          String responseJson = objectMapper.writeValueAsString(completion);
          JsonNode choicesNode = objectMapper.readTree(responseJson).get("choices");
          extractAndAppendContentForTeacherQuestionRecommendation(choicesNode, sb);
        } catch (Exception e) {
          log.error("Error processing response: ", e);
        }
      });
    } catch (Exception e) {
      log.error("Error in stream completion: ", e);
    }
    return sb.toString();
  }

  private void extractAndAppendContentForTeacherQuestionRecommendation(JsonNode choicesNode,
      StringBuilder sb) {
    choicesNode.forEach(choiceNode -> {
      JsonNode messageNode = choiceNode.get("message");
      if (messageNode != null && messageNode.has("content")) {
        JsonNode contentNode = messageNode.get("content");
        if (contentNode != null && !contentNode.isNull() && !contentNode.asText().isEmpty()) {
          String content = contentNode.asText();
          appendContent(content, sb);
        }
      }
    });
  }

  private void appendContent(String content, StringBuilder sb) {
    String modifiedContent = content.replace(" ", "ㅤ");
    sb.append(modifiedContent);
  }
}
