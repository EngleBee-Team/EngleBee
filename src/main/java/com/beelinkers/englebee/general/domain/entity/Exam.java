package com.beelinkers.englebee.general.domain.entity;

import com.beelinkers.englebee.common.BaseEntity;
import com.beelinkers.englebee.teacher.domain.entity.TeacherQuestion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    name = "EXAM_SEQ_GENERATOR",
    sequenceName = "EXAM_SEQ",
    initialValue = 1,
    allocationSize = 1
)
@Table(name = "EXAM")
@Entity
public class Exam extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXAM_SEQ_GENERATOR")
  private Long seq;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "lecture_seq", nullable = false)
  private Lecture lecture;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private ExamStatus status;

  @Column(name = "title", nullable = false, length = 50)
  private String title;

  @Column(name = "feedback", length = 500)
  private String feedback;

  @OneToMany(mappedBy = "exam", fetch = FetchType.LAZY)
  private List<TeacherQuestion> questions = new ArrayList<>();
}