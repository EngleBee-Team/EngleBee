package com.beelinkers.englebee.auth.domain.entity;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class LoginTypeTest {

  @Test
  @DisplayName("로그입 타입은 구글 로그인이 존재합니다.")
  public void isLoginTypeExistGoogle() {
    // given, when
    LoginType loginType = LoginType.GOOGLE;

    // then
    assertThat(loginType).isEqualTo(LoginType.GOOGLE);
  }

  @Test
  public void numTest() {
    String s = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, "
        + "17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, "
        + "33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, "
        + "50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, "
        + "72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, "
        + "101, 102, 104, 105, 107, 108, 110, 111, 113, 114, 116, 117, 119, 120, 122, 123, 125, 126, 128, 129, 131, 132, 134, 135, 137, 138, 140, 141, 143, 144, 146, 147, 149, 150, 152, 153, 155, 156, 158, 159, 161, 162, 164, 165, 167, 168, 170, 171, 173, 174, 176, 177, 179, 180, 182, 183, 185, 186, 187, 188, 190, 192, 194, 195, 197, 198, 199";
    System.out.println(Arrays.stream(s.split(", ")).count());
  }

}
