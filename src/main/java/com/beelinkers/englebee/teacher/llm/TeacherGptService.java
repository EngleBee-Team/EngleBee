package com.beelinkers.englebee.teacher.llm;


import com.beelinkers.englebee.teacher.dto.response.TeacherExamRegisterPageDTO;

public interface TeacherGptService {

  GptChatCompletionRequest createUserPromptForTeacherQuestionRecommendation(
      TeacherExamRegisterPageDTO teacherExamRegisterPageDTO);
}
