package com.beelinkers.englebee.general.domain.entity;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    name = "QUESTION_SEQ_GENERATOR",
    sequenceName = "QUESTION_SEQ",
    initialValue = 1,
    allocationSize = 1
)
@Table(name = "QUESTION")
@Entity
public class Question extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUESTION_SEQ_GENERATOR")
  private Long seq;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_seq", nullable = false)
  private Member member;

  @Column(name = "title", nullable = false, length = 100)
  private String title;

  @Column(name = "content", nullable = false, length = 3000)
  private String content;

  @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
  private List<Reply> replies = new ArrayList<>();

}