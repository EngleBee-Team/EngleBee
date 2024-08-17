package com.beelinkers.englebee.auth.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class StudentGradeTest {

  @Test
  @DisplayName("학생은 중학교 1학년일 수 있다.")
  public void 학생은_중학교_1학년일_수_있다() {
    // given, when, then
    assertThat(StudentGrade.SEVENTH_GRADE.getKoreanGrade()).isEqualTo("중1");
  }

  @Test
  @DisplayName("학생은 중학교 2학년일 수 있다.")
  public void 학생은_중학교_2학년일_수_있다() {
    // given, when, then
    assertThat(StudentGrade.EIGHTH_GRADE.getKoreanGrade()).isEqualTo("중2");
  }

  @Test
  @DisplayName("학생은 중학교 3학년일 수 있다.")
  public void 학생은_중학교_3학년일_수_있다() {
    // given, when, then
    assertThat(StudentGrade.NINTH_GRADE.getKoreanGrade()).isEqualTo("중3");
  }

  @Test
  @DisplayName("학생은 고등학교 1학년일 수 있다.")
  public void 학생은_고등학교_1학년일_수_있다() {
    // given, when, then
    assertThat(StudentGrade.TENTH_GRADE.getKoreanGrade()).isEqualTo("고1");
  }

  @Test
  @DisplayName("학생은 고등학교 2학년일 수 있다.")
  public void 학생은_고등학교_2학년일_수_있다() {
    // given, when, then
    assertThat(StudentGrade.ELEVENTH_GRADE.getKoreanGrade()).isEqualTo("고2");
  }

  @Test
  @DisplayName("학생은 고등학교 3학년일 수 있다.")
  public void 학생은_고등학교_3학년일_수_있다() {
    // given, when, then
    assertThat(StudentGrade.TWELFTH_GRADE.getKoreanGrade()).isEqualTo("고3");
  }

}
