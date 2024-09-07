package com.beelinkers.englebee.teacher.llm;

public class TeacherGptConstant {

  public static final String SYSTEM_PROMPT_TEACHER_QUESTION_RECOMMENDATION =
      "영어 시험 문제를 생성하시오. " +
          "문제는 다음 형식이어야 합니다: 시험문제, 선택지 4개, 정답, 출제의도. 마크다운 문법 금지. " +
          "시험문제 부분에서 ':' 이나, '?' 문장이 나오면 무조건 한 줄을 띄우시오. " +
          "각 선택지가 끝나면 한 줄을 띄운다. " +
          "문제의 종류는 %s 문제이다. " +
          "쉬운 문제의 경우 문제는 한 문장으로 이루어지고, 선택지는 1개당 최대 한 문장으로 이루어진다. " +
          "보통 수준의 문제의 경우 문제는 두 문단이어야하고, 선택지는 1개당 2문장으로 이루어진다. " +
          "어려운 수준의 문제의 경우 문제는 세 문단이어야하고, 선택지는 1개당 3문장으로 이루어진다. " +
          "시험문제의 선택지는 영어로 구성하시오. " +
          "쉬운 문제의 경우 문제는 한국어로 구성하시오. 한국어로 상황설명을 하고, 핵심적으로 물어볼 내용은 영어로 작성하시오." +
          "선택지는 영어로 구성하시오. " +
          "선택지와 선택지 사이에 절대 ',' 기호를 삽입하지 마시오." +
          "보통 수준의 문제의 경우 문제는 한국어 또는 영어로 구성하시오. 5:5 비율로 하시오. 선택지는 영어로 구성하시오. " +
          "어려운 수준의 문제의 경우 문제를 영어로 구성하시오. 선택지는 영어로 구성하시오. " +
          "어려운 수준의 문제의 경우 무조건 영어로 문제를 내야함을 명심해. " +
          "문제는 한 문제만 출제하시오. " +
          "문제 부분에서 '?' 문자가 나오면 그 이후에 \n을 삽입하여 한 줄 띄워." +
          "출제 의도는 한국어로 작성하시오. " +
          "다음 입력값에서 추론할 수 있는 문제 과목과 관계없이, 영어 어법, 문장, 단어 부분에 대해서 평가할 수 있는 문제만을 출제하시오.";

  // 사용자 프롬프트 형식
  public static final String USER_PROMPT_TEACHER_QUESTION_RECOMMENDATION_FORMAT =
      "대상: %s 학년, %s 과목, %s 수준.";
  // 문제 예시 포맷
  public static final String TEACHER_QUESTION_EXAMPLE_FORMAT =
      "[문제] \n\n %s \n\n [선택지] \n\n %s \n\n [정답] (%d) %s \n\n [출제의도] %s";
  // 선택지 포맷
  public static final String TEACHER_QUESTION_RECOMMENDATION_CHOICE_FORMAT = "(%d) %s \n";
  // 예시 헤더
  public static final String TEACHER_QUESTION_RECOMMENDATION_EXAMPLES_HEADER = "\n예시: \n";

  public static final String SYSTEM_ROLE = "system";
  public static final String USER_ROLE = "user";

}
