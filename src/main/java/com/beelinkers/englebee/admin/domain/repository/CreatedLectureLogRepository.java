package com.beelinkers.englebee.admin.domain.repository;

import com.beelinkers.englebee.admin.domain.entity.CreatedLectureLog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CreatedLectureLogRepository extends JpaRepository<CreatedLectureLog, Long> {

  @Query(value = "SELECT DAY(c.created_at), COUNT(*) " +
      "FROM created_lecture_log c " +
      "WHERE YEAR(c.created_at) = :year " +
      "AND MONTH(c.created_at) = :month " +
      "GROUP BY DAY(c.created_at)", nativeQuery = true)
  List<Object[]> findCreateLectureGroupByDay(@Param("year") int year,@Param("month") int month);
}
