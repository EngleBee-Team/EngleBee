package com.beelinkers.englebee.auth.domain.entity;

import com.beelinkers.englebee.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
    name = "MEMBER_SEQ_GENERATOR",
    sequenceName = "MEMBER_SEQ",
    initialValue = 1,
    allocationSize = 1
)
@Table(
    name = "MEMBER",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "email", name = "UNIQUE_EMAIL_CONSTRAINT"),
        @UniqueConstraint(columnNames = "nickname", name = "UNIQUE_NICKNAME_CONSTRAINT")
    }
)
@Entity
public class Member extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
  private Long seq;

  @Column(name = "nickname", length = 20, nullable = false)
  private String nickname;

  @Column(name = "email", length = 100, nullable = false)
  private String email;

  @Column(name = "profile", length = 255)
  private String profile;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", length = 20, nullable = false)
  private Role role;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", length = 20, nullable = false)
  private MemberStatus status;

  @Enumerated(EnumType.STRING)
  @Column(name = "grade", length = 20)
  private StudentGrade grade;

  @Column(name = "refresh_token", length = 255)
  private String refreshToken;

  @Enumerated(EnumType.STRING)
  @Column(name = "login_type", length = 20)
  private LoginType loginType;

  @Column(name = "personal_info_collection_agreed", nullable = false)
  private Boolean personalInfoCollectionAgreed;

  @Builder
  public Member(String nickname, String email, Role role, StudentGrade grade,
      LoginType loginType, Boolean personalInfoCollectionAgreed) {
    this.nickname = nickname;
    this.email = email;
    this.role = role;
    this.status = MemberStatus.NORMAL;
    this.grade = grade;
    this.loginType = loginType;
    this.personalInfoCollectionAgreed = personalInfoCollectionAgreed;
  }

  public void updateProfile(String profileUrl) {
    this.profile = profileUrl;
  }

  public void updateRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public void signOut() {
    this.status = MemberStatus.DEACTIVATED;
  }

  public boolean isAdmin() {
    return role.isAdmin();
  }

  public boolean isStudent() {
    return role.isStudent();
  }

  public boolean isTeacher() {
    return role.isTeacher();
  }

  public boolean isActive() {
    return status.isActive();
  }

  public boolean isDeactivated() {
    return status.isDeActivated();
  }


}