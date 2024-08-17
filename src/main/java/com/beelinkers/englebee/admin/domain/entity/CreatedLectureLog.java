package com.beelinkers.englebee.admin.domain.entity;

import com.beelinkers.englebee.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    name = "CREATED_LECTURE_LOG_SEQ_GENERATOR",
    sequenceName = "CREATED_LECTURE_LOG_SEQ",
    initialValue = 1,
    allocationSize = 1
)
@Table(name = "CREATED_LECTURE_LOG")
@Entity
public class CreatedLectureLog extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREATED_LECTURE_LOG_SEQ_GENERATOR")
  private Long seq;

  @Column(name = "teacher_seq", nullable = false)
  private Long teacherSeq;

  @Column(name = "student_seq", nullable = false)
  private Long studentSeq;

}