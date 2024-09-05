package com.beelinkers.englebee.general.domain.repository;

import com.beelinkers.englebee.general.domain.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface QuestionRepository extends JpaRepository<Question, Long> {

  Page<Question> findAllByOrderByCreatedAtDesc(@NonNull Pageable pageable);
  
  Page<Question> findByMemberSeqOrderByCreatedAtDesc(Long memberSeq, Pageable pageable);
}
