package com.beelinkers.englebee.teacher.llm;

import static com.beelinkers.englebee.teacher.llm.TeacherGptConstant.SYSTEM_PROMPT_FEEDBACK_RECOMMENDATION;
import static com.beelinkers.englebee.teacher.llm.TeacherGptConstant.SYSTEM_PROMPT_TEACHER_QUESTION_RECOMMENDATION;
import static com.beelinkers.englebee.teacher.llm.TeacherGptConstant.SYSTEM_ROLE;

import com.theokanning.openai.completion.chat.ChatMessage;
import java.util.ArrayList;
import java.util.List;

public class TeacherGptUtil {

  public static List<ChatMessage> getSystemPromptForTeacherQuestionRecommendation(
      String difficulty) {
    List<ChatMessage> chatMessages = new ArrayList<>();
    chatMessages.add(new ChatMessage(SYSTEM_ROLE,
        String.format(SYSTEM_PROMPT_TEACHER_QUESTION_RECOMMENDATION, difficulty)));
    return chatMessages;
  }

  public static List<ChatMessage> getSystemPromptForFeedbackRecommendation(
      String angryIndex) {
    List<ChatMessage> chatMessages = new ArrayList<>();
    chatMessages.add(new ChatMessage(SYSTEM_ROLE,
        String.format(SYSTEM_PROMPT_FEEDBACK_RECOMMENDATION, angryIndex)));
    return chatMessages;
  }
}
