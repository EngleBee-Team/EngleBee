package com.beelinkers.englebee.teacher.llm;

public class TeacherGptUtil {

  public static final String SYSTEM_PROMPT_TEACHER_QUESTION_RECOMMENDATION_FORMAT =
      "영어 시험 문제를 생성하시오. 대상: %s 학년, %s 과목, %s 수준. " +
          "문제는 다음 형식이어야 합니다: 문제, 선택지 4개, 정답, 출제의도. 마크다운 문법 금지." +
          "예시: \n %s";
  /* 사용 방법
  String systemPrompt = String.format(
    SYSTEM_PROMPT_TEACHER_QUESTION_RECOMMENDATION_FORMAT,
    grade, subject, level, teacherQuestionDetails
  );
  */
  /*
  예시 ( TeacherQuestion 에서 뽑아올 값 )
  "문제: What is the capital of France? \n" +
  "선택지: (1) Berlin, (2) Madrid, (3) Paris, (4) Rome \n" +
  "정답: (3) Paris \n" +
  "출제의도: 학생들이 주요 국가의 수도를 학습하게 하기 위함."
  */

  public static final String SYSTEM = "system";

}
