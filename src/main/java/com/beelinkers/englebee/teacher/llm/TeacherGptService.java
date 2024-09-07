package com.beelinkers.englebee.teacher.llm;


import java.util.List;

public interface TeacherGptService {

  GptChatCompletionRequest createUserPromptForTeacherQuestionRecommendation(
      String studentGrade, String subjectCode, String levelCode);

  GptChatCompletionRequest createUserPromptForFeedbackRecommendation(
      String studentGrade, List<Long> teacherQuestionSeqs);
}
