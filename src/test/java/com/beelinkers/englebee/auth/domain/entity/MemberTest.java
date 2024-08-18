package com.beelinkers.englebee.auth.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberTest {

  @Test
  @DisplayName("멤버의 종류에는 ADMIN이 있고, 이를 확인할 수 있다.")
  public void 멤버의_종류에는_ADMIN이_있고_이를_확인할_수_있다() {
    // given
    Member member = Member.builder()
        .nickname("admin_nickname")
        .email("admin@example.com")
        .role(Role.ADMIN)
        .personalInfoCollectionAgreed(true)
        .build();

    // when, then
    assertThat(member.isAdmin()).isTrue();
    assertThat(member.isStudent()).isFalse();
    assertThat(member.isTeacher()).isFalse();
  }

  @Test
  @DisplayName("멤버의 종류에는 STUDENT가 있고, 이를 확인할 수 있다.")
  public void 멤버의_종류에는_STUDENT가_있고_이를_확인할_수_있다() {
    // given
    Member member = Member.builder()
        .nickname("student_nickname")
        .email("student@example.com")
        .role(Role.STUDENT)
        .personalInfoCollectionAgreed(true)
        .build();

    // when, then
    assertThat(member.isAdmin()).isFalse();
    assertThat(member.isStudent()).isTrue();
    assertThat(member.isTeacher()).isFalse();
  }

  @Test
  @DisplayName("멤버의 종류에는 TEACHER가 있고, 이를 확인할 수 있다.")
  public void 멤버의_종류에는_TEACHER가_있고_이를_확인할_수_있다() {
    // given
    Member member = Member.builder()
        .nickname("teacher_nickname")
        .email("teacher@example.com")
        .role(Role.TEACHER)
        .personalInfoCollectionAgreed(true)
        .build();

    // when, then
    assertThat(member.isAdmin()).isFalse();
    assertThat(member.isStudent()).isFalse();
    assertThat(member.isTeacher()).isTrue();
  }

  @Test
  @DisplayName("멤버의 상태는 활동 중일 수 있고, 이를 확인할 수 있다.")
  public void 멤버의_상태는_활동_중일_수_있고_이를_확인할_수_있다() {
    // given
    Member member = Member.builder()
        .nickname("nickname")
        .email("email@example.com")
        .role(Role.STUDENT)
        .personalInfoCollectionAgreed(true)
        .build();

    // when, then
    assertThat(member.isActive()).isTrue();
    assertThat(member.isDeactived()).isFalse();
  }

  @Test
  @DisplayName("멤버의 상태는 회원 탈퇴 상태일 수 있고, 이를 확인할 수 있다.")
  public void 멤버의_상태는_회원_탈퇴_상태일_수_있고_이를_확인할_수_있다() {
    // given
    Member member = Member.builder()
        .nickname("nickname")
        .email("email@example.com")
        .role(Role.STUDENT)
        .personalInfoCollectionAgreed(true)
        .build();

    // when
    member.signOut();

    // then
    assertThat(member.isActive()).isFalse();
    assertThat(member.isDeactived()).isTrue();
  }

  @Test
  @DisplayName("멤버 중 STUDNET와 TEACHER는 프로필 사진을 변경할 수 있다.")
  public void 멤버_중_STUDENT와_TEACHER는_프로필_사진을_변경할_수_있다() {
    // given
    Member member = Member.builder()
        .nickname("nickname")
        .email("email@example.com")
        .role(Role.TEACHER)
        .personalInfoCollectionAgreed(true)
        .build();

    // when
    member.updateProfile("http://example.com/profile.jpg");

    // then
    assertThat(member.getProfile()).isEqualTo("http://example.com/profile.jpg");
  }

  @Test
  @DisplayName("멤버는 Refresh Token을 발급 혹은 재발급 받을 수 있다.")
  public void 멤버는_Refresh_Token을_발급_혹은_재발급_받을_수_있다() {
    // given
    Member member = Member.builder()
        .nickname("nickname")
        .email("email@example.com")
        .role(Role.STUDENT)
        .personalInfoCollectionAgreed(true)
        .build();

    // when
    member.updateRefreshToken("new_refresh_token");

    // then
    assertThat(member.getRefreshToken()).isEqualTo("new_refresh_token");
  }
}
