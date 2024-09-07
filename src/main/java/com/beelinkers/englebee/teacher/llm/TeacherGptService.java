package com.beelinkers.englebee.teacher.llm;


public interface TeacherGptService {

  GptChatCompletionRequest createUserPromptForTeacherQuestionRecommendation(
      String studentGrade, String subjectCode, String levelCode);
}
