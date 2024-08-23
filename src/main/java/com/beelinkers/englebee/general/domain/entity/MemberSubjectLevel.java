package com.beelinkers.englebee.general.domain.entity;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
    name = "MEMBER_SUBJECT_LEVEL_SEQ_GENERATOR",
    sequenceName = "MEMBER_SUBJECT_LEVEL_SEQ",
    initialValue = 1,
    allocationSize = 1
)
@Table(name = "MEMBER_SUBJECT_LEVEL")
@Entity
public class MemberSubjectLevel extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SUBJECT_LEVEL_SEQ_GENERATOR")
  private Long seq;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "student_seq", nullable = false)
  private Member student;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "subject_level_seq", nullable = false)
  private SubjectLevel subjectLevel;

  @Builder
  public MemberSubjectLevel(Member student, SubjectLevel subjectLevel) {
    this.student = student;
    this.subjectLevel = subjectLevel;
  }
}
