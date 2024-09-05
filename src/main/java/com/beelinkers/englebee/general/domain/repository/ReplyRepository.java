package com.beelinkers.englebee.general.domain.repository;

import com.beelinkers.englebee.general.domain.entity.Reply;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

  @Query("SELECT r FROM Reply r " +
      "JOIN FETCH r.question q " +
      "JOIN FETCH q.member m " +
      "WHERE r.member.seq = :memberSeq " +
      "ORDER BY r.createdAt ASC")
  Page<Reply> findRepliesByMemberSeq(@Param("memberSeq") Long memberSeq,
      Pageable pageable);

  List<Reply> findByQuestionSeqOrderByCreatedAtDesc(Long questionSeq);

}
