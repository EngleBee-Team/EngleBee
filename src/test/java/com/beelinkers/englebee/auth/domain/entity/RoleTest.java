package com.beelinkers.englebee.auth.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RoleTest {

  @Test
  @DisplayName("유저의 역할에는 ADMIN이 있습니다.")
  public void 유저의_역할에는_ADMIN이_있습니다() {
    // given, when, then
    assertThat(Role.ADMIN.getKey()).isEqualTo("ROLE_ADMIN");
    assertThat(Role.ADMIN.isAdmin()).isTrue();
    assertThat(Role.ADMIN.isStudent()).isFalse();
    assertThat(Role.ADMIN.isTeacher()).isFalse();
  }

  @Test
  @DisplayName("유저의 역할에는 STUDENT이 있습니다.")
  public void 유저의_역할에는_STUDENT이_있습니다() {
    // given, when, then
    assertThat(Role.STUDENT.getKey()).isEqualTo("ROLE_STUDENT");
    assertThat(Role.STUDENT.isAdmin()).isFalse();
    assertThat(Role.STUDENT.isStudent()).isTrue();
    assertThat(Role.STUDENT.isTeacher()).isFalse();
  }

  @Test
  @DisplayName("유저의 역할에는 TEACHER가 있습니다.")
  public void 유저의_역할에는_TEACHER가_있습니다() {
    // given, when, then
    assertThat(Role.TEACHER.getKey()).isEqualTo("ROLE_TEACHER");
    assertThat(Role.TEACHER.isAdmin()).isFalse();
    assertThat(Role.TEACHER.isStudent()).isFalse();
    assertThat(Role.TEACHER.isTeacher()).isTrue();
  }
}
