package com.beelinkers.englebee.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NicknameCheckRequestDTO {

  @NotNull(message = "닉네임 입력값이 존재하지 않습니다.")
  @NotBlank(message = "닉네임 값은 비어있으면 안됩니다.")
  @Length(min = 1, max = 20, message = "닉네임의 길이는 1글자 ~ 20글자 사이여야 합니다.")
  private String nickname;
}
