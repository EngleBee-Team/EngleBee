package com.beelinkers.englebee.general.domain.entity;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
    name = "LECTURE_SEQ_GENERATOR",
    sequenceName = "LECTURE_SEQ",
    initialValue = 1,
    allocationSize = 1
)
@Table(name = "LECTURE")
@Entity
public class Lecture extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LECTURE_SEQ_GENERATOR")
  private Long seq;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "teacher_seq", nullable = false)
  private Member teacher;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "student_seq", nullable = false)
  private Member student;

  @Column(name = "title", nullable = false, length = 100)
  private String title;

  @OneToMany(mappedBy = "lecture", fetch = FetchType.LAZY)
  private List<LectureSubjectLevel> subjectLevels = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private LectureStatus status;
}