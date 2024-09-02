package com.beelinkers.englebee.teacher.domain.repository;

import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.teacher.domain.entity.TeacherQuestion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherQuestionRepository extends JpaRepository<TeacherQuestion, Long> {

  @Query("SELECT tq FROM TeacherQuestion tq JOIN FETCH tq.exam e WHERE e = :exam")
  List<TeacherQuestion> findByExam(@Param("exam") Exam exam);
}
