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

  public static final String SYSTEM_PROMPT_FEEDBACK_RECOMMENDATION =
      "학생에게 줄 피드백을 작성하시오." +
          "피드백은 한국어로만 작성하시오." +
          "피드백에는 마크다운 문법 금지." +
          "피드백의 내용은 %s하게 작성해야 한다." +
          "피드백 내용에서 학생의 학년을 언급하고, 주어진 문제에 대해 그에 맞는 피드백을 진행한다." +
          "피드백의 내용이 보통맛이라는 것은 순한맛과 매운맛의 중간 정도의 강도, 어조를 말한다." +
          "피드백의 내용이 폭탄맛이라는 것은 매우 긴 한숨으로 시작한다."
          + "매운맛의 피드백의 것보다 강도가 2배 높게, 크게 혼내는 어조와 말투를 사용한다." +
          "모든 문제를 다 맞은 경우, 순한맛에서는 매우 칭찬을 해주고, 보통맛에서도 칭찬을 해주며, 매운맛과 폭탄맛에서는 자만하지 않도록 주의를 주거나, "
          + "그와 비슷한 내용을 전달할 때의 어조와 말투를 사용한다.";


  public static final String USER_PROMPT_TEACHER_QUESTION_RECOMMENDATION_FORMAT =
      "대상: %s 학년, %s 과목, %s 수준.";

  public static final String TEACHER_QUESTION_EXAMPLE_FORMAT =
      "[문제] \n\n %s \n\n [선택지] \n\n %s \n\n [정답] (%d) %s \n\n [출제의도] %s";

  public static final String TEACHER_QUESTION_RECOMMENDATION_CHOICE_FORMAT = "(%d) %s \n";

  public static final String TEACHER_QUESTION_RECOMMENDATION_EXAMPLES_HEADER = "\n예시: \n";

  public static final String TEACHER_QUESTION_FOR_FEEDBACK_EXAMPLE_FORMAT =
      "[학생의 학년] %s [문제] %s \n [선택지] \n %s \n [정답] %s [학생답] %s \n [출제의도] %s";
  public static final String SYSTEM_ROLE = "system";
  public static final String USER_ROLE = "user";

}
