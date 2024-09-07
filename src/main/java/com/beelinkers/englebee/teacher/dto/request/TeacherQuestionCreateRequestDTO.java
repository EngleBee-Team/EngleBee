package com.beelinkers.englebee.teacher.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherQuestionCreateRequestDTO {

  @NotNull(message = "문제 지시사항 값은 필수 값입니다.")
  @Length(min = 1, max = 500, message = "문제 지시사항 최소 1글자 이상 ~ 최대 500글자 이하로 입력할 수 있습니다.")
  private String direction;

  @NotNull(message = "선택지 리스트 값은 필수 값입니다.")
  @Size(min = 4, max = 4)
  private List<@NotNull(message = "선택지 값은 필수 값입니다.") @Length(min = 1, max = 1000, message = "각 선택지는 최소 1글자 ~ 1000글자까지 입력할 수 있습니다.") String> choices;

  @NotNull(message = "정답 값은 필수 값입니다.")
  @Min(value = 1, message = "정답 값은 1 이상이어야 합니다.")
  @Max(value = 4, message = "정답 값은 4 이하여야 합니다.")
  private Integer correctAnswer;

  @NotNull(message = "출제 의도 값은 필수 값입니다.")
  @Length(min = 1, max = 2000, message = "출제 의도는 최소 1글자, 최대 2000글자 입력할 수 있습니다.")
  private String intent;

}
