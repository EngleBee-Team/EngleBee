package com.beelinkers.englebee.teacher.llm;

import static com.beelinkers.englebee.teacher.llm.TeacherGptConstant.TEACHER_QUESTION_EXAMPLE_FORMAT;
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
import com.beelinkers.englebee.teacher.dto.response.TeacherExamRegisterPageDTO;
import com.beelinkers.englebee.teacher.exception.SubjectLevelNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
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

  private final Random random;
  private final SubjectLevelRepository subjectLevelRepository;
  private final TeacherQuestionRepository teacherQuestionRepository;

  @Override
  @Transactional(readOnly = true)
  public GptChatCompletionRequest createUserPromptForTeacherQuestionRecommendation(
      TeacherExamRegisterPageDTO teacherExamRegisterPageDTO) {
    Map<String, String> studentSubjectLevels = teacherExamRegisterPageDTO.getStudentSubjectLevels();
    Map<String, String> lectureSubjectLevels = teacherExamRegisterPageDTO.getLectureSubjectLevels();

    StudentGrade studentGrade = StudentGrade.fromKoreanGrade(
        teacherExamRegisterPageDTO.getStudentGrade());
    // 공통 과목 레벨 구하기
    List<String> commonSubjects = new ArrayList<>(studentSubjectLevels.keySet());
    commonSubjects.retainAll(lectureSubjectLevels.keySet());

    // 공통 키 중 랜덤으로 하나 선택해서 해당 키-값 쌍 출력
    SubjectCode randomSubjectCode = SubjectCode.fromKoreanCode(
        commonSubjects.get(random.nextInt(commonSubjects.size())));
    LevelCode randomLevelCode = LevelCode.fromKoreanCode(
        studentSubjectLevels.get(randomSubjectCode.name()));
    SubjectLevel subjectLevel = subjectLevelRepository.findBySubjectCodeAndLevelCode(
            randomSubjectCode, randomLevelCode)
        .orElseThrow(() -> new SubjectLevelNotFoundException("해당하는 과목레벨이 없습니다."));

    // 해당 과목과 레벨, 학생의 학년에 맞는 문제를 랜덤으로 최대 3개 조회
    List<TeacherQuestion> randomQuestions = teacherQuestionRepository
        .findRandomQuestionsBySubjectLevelAndStudentGrade(
            subjectLevel, studentGrade, PageRequest.of(0, 3));

    // userPrompt 생성
    String userPrompt = String.format(
        USER_PROMPT_TEACHER_QUESTION_RECOMMENDATION_FORMAT,
        studentGrade.getKoreanGrade(),
        randomSubjectCode.getKoreanCode(),
        randomLevelCode.getKoreanCode()
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

    log.info("userPrompt = {}", userPrompt);

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
