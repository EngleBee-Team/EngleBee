package com.beelinkers.englebee.general.domain.repository;

import com.beelinkers.englebee.general.domain.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    // 전체 리스트
    Page<Question> findAll(@NonNull Pageable pageable);

}
