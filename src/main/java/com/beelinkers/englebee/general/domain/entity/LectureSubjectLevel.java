package com.beelinkers.englebee.general.domain.entity;

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
    name = "LECTURE_SUBJECT_LEVEL_SEQ_GENERATOR",
    sequenceName = "LECTURE_SUBJECT_LEVEL_SEQ",
    initialValue = 1,
    allocationSize = 1
)
@Table(name = "LECTURE_SUBJECT_LEVEL")
@Entity
public class LectureSubjectLevel extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LECTURE_SUBJECT_LEVEL_SEQ_GENERATOR")
  private Long seq;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "subject_level_seq", nullable = false)
  private SubjectLevel subjectLevel;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "lecture_seq", nullable = false)
  private Lecture lecture;

  @Builder
  public LectureSubjectLevel(Lecture lecture, SubjectLevel subjectLevel) {
    this.subjectLevel = subjectLevel;
    this.lecture = lecture;
    lecture.getSubjectLevels().add(this);
  }
}