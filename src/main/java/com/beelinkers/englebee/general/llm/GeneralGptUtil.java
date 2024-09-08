package com.beelinkers.englebee.general.llm;

import static com.beelinkers.englebee.general.llm.GeneralGptConstant.MAKE_REPLY_TO_QUESTION_PROMPT;
import static com.beelinkers.englebee.teacher.llm.TeacherGptConstant.SYSTEM_ROLE;

import com.theokanning.openai.completion.chat.ChatMessage;
import java.util.ArrayList;
import java.util.List;

public class GeneralGptUtil {

  public static List<ChatMessage> getSystemPromptForTeacherQuestionRecommendation() {
    List<ChatMessage> chatMessages = new ArrayList<>();
    chatMessages.add(new ChatMessage(SYSTEM_ROLE, MAKE_REPLY_TO_QUESTION_PROMPT));
    return chatMessages;
  }
}
