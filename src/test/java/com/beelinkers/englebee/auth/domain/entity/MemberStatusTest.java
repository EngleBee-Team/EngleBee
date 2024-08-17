package com.beelinkers.englebee.auth.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberStatusTest {

  @Test
  @DisplayName("회원의 상태에는 활성화 상태가 있다.")
  public void 회원의_상태에는_활성화_상태가_있다() {
    // given
    MemberStatus status = MemberStatus.NORMAL;

    // when, then
    assertThat(status.isActive()).isTrue();
    assertThat(status.isDeActivated()).isFalse();
  }

  @Test
  @DisplayName("회원의 상태에는 회원 탈퇴 상태가 있다.")
  public void 회원의_상태에는_회원_탈퇴_상태가_있다() {
    // given
    MemberStatus status = MemberStatus.DEACTIVATED;

    // when, then
    assertThat(status.isActive()).isFalse();
    assertThat(status.isDeActivated()).isTrue();
  }
}
