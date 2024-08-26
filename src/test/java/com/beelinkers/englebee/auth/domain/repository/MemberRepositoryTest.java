package com.beelinkers.englebee.auth.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.auth.domain.entity.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Transactional
public class MemberRepositoryTest {

  @Autowired
  private MemberRepository memberRepository;

  @Test
  @DisplayName("멤버를 추가할 수 있다.")
  public void 멤버를_추가할_수_있다() {
    // given
    Member member = Member.builder()
        .nickname("test_nickname")
        .email("test@example.com")
        .role(Role.STUDENT)
        .personalInfoCollectionAgreed(true)
        .build();

    // when
    Member savedMember = memberRepository.save(member);
    
    // then
    assertThat(savedMember).isNotNull();
    assertThat(savedMember.getSeq()).isNotNull();
    assertThat(savedMember.getNickname()).isEqualTo("test_nickname");
    assertThat(savedMember.getEmail()).isEqualTo("test@example.com");
    assertThat(savedMember.getRole()).isEqualTo(Role.STUDENT);
  }

  @Test
  @DisplayName("멤버를 추가하고 ID로 조회할 수 있다.")
  public void 멤버를_추가하고_ID로_조회할_수_있다() {
    // given
    Member member = Member.builder()
        .nickname("test_nickname")
        .email("test@example.com")
        .role(Role.TEACHER)
        .personalInfoCollectionAgreed(true)
        .build();

    // when
    memberRepository.save(member);
    Member foundMember = memberRepository.findById(member.getSeq()).orElse(null);

    // then
    assertThat(foundMember).isNotNull();
    assertThat(foundMember.getNickname()).isEqualTo("test_nickname");
    assertThat(foundMember.getEmail()).isEqualTo("test@example.com");
    assertThat(foundMember.getRole()).isEqualTo(Role.TEACHER);
  }
}