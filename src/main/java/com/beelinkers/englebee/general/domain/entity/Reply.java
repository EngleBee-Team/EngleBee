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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
    name = "REPLY_SEQ_GENERATOR",
    sequenceName = "REPLY_SEQ",
    initialValue = 1,
    allocationSize = 1
)
@Table(name = "REPLY")
@Entity
public class Reply extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPLY_SEQ_GENERATOR")
  private Long seq;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_seq", nullable = false)
  private Question question;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_seq", nullable = false)
  private Member member;

  @Column(name = "content", length = 2000, nullable = false)
  private String content;

  @Builder
  public Reply(Question question, Member author, String content) {
    this.question = question;
    this.member = author;
    this.content = content;
    question.getReplies().add(this);
  }

}
