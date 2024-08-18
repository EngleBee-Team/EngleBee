package com.beelinkers.englebee.general.domain.entity;

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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
    name = "SUBJECT_LEVEL_SEQ_GENERATOR",
    sequenceName = "SUBJECT_LEVEL_SEQ",
    initialValue = 1,
    allocationSize = 1
)
@Table(name = "SUBJECT_LEVEL")
@Entity
public class SubjectLevel extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBJECT_LEVEL_SEQ_GENERATOR")
  private Long seq;

  @Enumerated(EnumType.STRING)
  @Column(name = "subject_code", nullable = false)
  private SubjectCode subjectCode;

  @Enumerated(EnumType.STRING)
  @Column(name = "level_code", nullable = false)
  private LevelCode levelCode;
}