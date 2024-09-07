package com.beelinkers.englebee.teacher.llm;

import static com.beelinkers.englebee.teacher.llm.TeacherGptConstant.USER_ROLE;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GptChatCompletionRequest {

  private static final String MODEL = "gpt-4o-mini";
  private static final Integer MAX_TOKENS = 3_000;
  private String message;

  public static ChatCompletionRequest of(List<ChatMessage> systemPrompt,
      GptChatCompletionRequest userRequest) {
    return ChatCompletionRequest.builder()
        .model(MODEL)
        .messages(convertChatMessage(systemPrompt, userRequest))
        .maxTokens(MAX_TOKENS)
        .build();
  }

  private static List<ChatMessage> convertChatMessage(List<ChatMessage> systemPrompt,
      GptChatCompletionRequest userRequest) {
    systemPrompt.add(new ChatMessage(USER_ROLE, userRequest.getMessage()));
    return systemPrompt;
  }
}
