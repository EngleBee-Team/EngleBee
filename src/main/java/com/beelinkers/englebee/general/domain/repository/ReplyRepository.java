package com.beelinkers.englebee.general.domain.repository;

import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.general.domain.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

  Page<Reply> findByQuestionMemberOrderByCreatedAt(Member memberSeq, Pageable pageable);
}
