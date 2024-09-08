package com.beelinkers.englebee.teacher.llm;

import static com.beelinkers.englebee.teacher.llm.TeacherGptConstant.TEACHER_QUESTION_EXAMPLE_FORMAT;
import static com.beelinkers.englebee.teacher.llm.TeacherGptConstant.TEACHER_QUESTION_FOR_FEEDBACK_EXAMPLE_FORMAT;
import static com.beelinkers.englebee.teacher.llm.TeacherGptConstant.TEACHER_QUESTION_RECOMMENDATION_CHOICE_FORMAT;
import static com.beelinkers.englebee.teacher.llm.TeacherGptConstant.TEACHER_QUESTION_RECOMMENDATION_EXAMPLES_HEADER;
import static com.beelinkers.englebee.teacher.llm.TeacherGptConstant.USER_PROMPT_TEACHER_QUESTION_RECOMMENDATION_FORMAT;

import com.beelinkers.englebee.auth.domain.entity.StudentGrade;
import com.beelinkers.englebee.general.domain.entity.LevelCode;
import com.beelinkers.englebee.general.domain.entity.SubjectCode;
import com.beelinkers.englebee.general.domain.entity.SubjectLevel;
import com.beelinkers.englebee.general.domain.repository.SubjectLevelRepository;
import com.beelinkers.englebee.teacher.domain.entity.TeacherQuestion;
import com.beelinkers.englebee.teacher.domain.repository.TeacherQuestionRepository;
import com.beelinkers.englebee.teacher.exception.SubjectLevelNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeacherGptServiceImpl implements TeacherGptService {

  private final SubjectLevelRepository subjectLevelRepository;
  private final TeacherQuestionRepository teacherQuestionRepository;

  @Override
  @Transactional(readOnly = true)
  public GptChatCompletionRequest createUserPromptForTeacherQuestionRecommendation(
      String studentGradeStr, String subjectCodeStr, String levelCodeStr) {
    StudentGrade studentGrade = StudentGrade.fromKoreanGrade(studentGradeStr);
    SubjectCode subjectCode = SubjectCode.fromKoreanCode(subjectCodeStr);
    LevelCode levelCode = LevelCode.fromKoreanCode(levelCodeStr);
    SubjectLevel subjectLevel = subjectLevelRepository.findBySubjectCodeAndLevelCode(subjectCode,
        levelCode).orElseThrow(() -> new SubjectLevelNotFoundException("해당하는 과목레벨이 없습니다."));

    List<TeacherQuestion> randomQuestions = teacherQuestionRepository
        .findRandomQuestionsBySubjectLevelAndStudentGrade(
            subjectLevel, studentGrade, PageRequest.of(0, 3));

    // userPrompt 생성
    String userPrompt = String.format(
        USER_PROMPT_TEACHER_QUESTION_RECOMMENDATION_FORMAT,
        studentGrade.getKoreanGrade(),
        subjectCode.getKoreanCode(),
        levelCode.getKoreanCode()
    );

    if (!randomQuestions.isEmpty()) {
      String examples = randomQuestions.stream()
          .map(question -> String.format(
              TEACHER_QUESTION_EXAMPLE_FORMAT,
              question.getDirection(),
              formatChoices(question.getChoices()),
              question.getCorrectAnswer(),
              extractCorrectAnswer(question.getChoices(), question.getCorrectAnswer()),
              question.getIntent()
          ))
          .collect(Collectors.joining("\n\n"));
      userPrompt += TEACHER_QUESTION_RECOMMENDATION_EXAMPLES_HEADER + examples;
    }

    log.info("TeacherQuestion Recommendation userPrompt = {}", userPrompt);

    return new GptChatCompletionRequest(userPrompt);
  }

  @Override
  @Transactional(readOnly = true)
  public GptChatCompletionRequest createUserPromptForFeedbackRecommendation(String studentGradeStr,
      List<Long> teacherQuestionSeqs) {

    StudentGrade studentGrade = StudentGrade.fromKoreanGrade(studentGradeStr);

    List<TeacherQuestion> teacherQuestions = teacherQuestionRepository.findBySeqIn(
        teacherQuestionSeqs);

    StringBuilder userPromptBuilder = new StringBuilder();

    for (TeacherQuestion question : teacherQuestions) {
      String[] choicesArray = question.getChoices().split(",");

      StringBuilder choicesFormatted = new StringBuilder();
      for (int i = 0; i < choicesArray.length; i++) {
        choicesFormatted.append(String.format("%d. %s\n", i + 1, choicesArray[i]));
      }

      String formattedQuestion = String.format(
          TEACHER_QUESTION_FOR_FEEDBACK_EXAMPLE_FORMAT,
          studentGrade.getKoreanGrade(),
          question.getDirection(),
          choicesFormatted.toString(),
          question.getCorrectAnswer(),
          question.getStudentAnswer() != null ? question.getStudentAnswer() : "응답없음",
          question.getIntent() != null ? question.getIntent() : "없음"
      );
      userPromptBuilder.append(formattedQuestion).append("\n\n");
    }

    String userPrompt = userPromptBuilder.toString();

    log.info("Feedback Recommendation userPrompt = {}", userPrompt);

    return new GptChatCompletionRequest(userPrompt);
  }


  private String formatChoices(String choices) {
    String[] choiceArray = choices.split(",");
    StringBuilder formattedChoices = new StringBuilder();
    for (int i = 0; i < choiceArray.length; i++) {
      formattedChoices.append(String.format(TEACHER_QUESTION_RECOMMENDATION_CHOICE_FORMAT, i + 1,
          choiceArray[i].trim()));
      if (i < choiceArray.length - 1) {
        formattedChoices.append(", ");
      }
    }
    return formattedChoices.toString();
  }

  private String extractCorrectAnswer(String choices, Integer correctAnswer) {
    String[] choiceArray = choices.split(",");
    if (correctAnswer > 0 && correctAnswer <= choiceArray.length) {
      return choiceArray[correctAnswer - 1].trim();
    }
    return "";
  }
}
