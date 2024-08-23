package com.beelinkers.englebee.teacher.domain.entity;

import com.beelinkers.englebee.common.BaseEntity;
import com.beelinkers.englebee.general.domain.entity.Exam;
import jakarta.persistence.Column;
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
    name = "TEACHER_QUESTION_SEQ_GENERATOR",
    sequenceName = "TEACHER_QUESTION_SEQ",
    initialValue = 1,
    allocationSize = 1
)
@Table(name = "TEACHER_QUESTION")
@Entity
public class TeacherQuestion extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEACHER_QUESTION_SEQ_GENERATOR")
  private Long seq;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "exam_seq", nullable = false)
  private Exam exam;

  @Column(name = "direction", nullable = false, length = 2000)
  private String direction;

  @Column(name = "choices", nullable = false, length = 300)
  private String choices; // ','로 구분한다, DB에서 가져올 때 split() 필요

  @Column(name = "correct_answer", nullable = false)
  private Integer correctAnswer;

  @Column(name = "intent", length = 500)
  private String intent;

  @Column(name = "student_answer")
  private Integer studentAnswer;

  @Builder
  public TeacherQuestion(String direction, String choices, Integer correctAnswer,
      String intent) {
    this.direction = direction;
    this.choices = choices;
    this.correctAnswer = correctAnswer;
    this.intent = intent;
  }

  public void registerToExam(Exam exam) {
    this.exam = exam;
    exam.getTeacherQuestions().add(this);
  }

  public void insertStudentAnswer(Integer studentAnswer) {
    this.studentAnswer = studentAnswer;
  }

}