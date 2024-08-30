package com.beelinkers.englebee.admin.domain.repository;

import com.beelinkers.englebee.admin.domain.entity.CallLLMLog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CallLLMLogRepository extends JpaRepository<CallLLMLog, Long> {

   @Query(value = "SELECT DAY(c.created_at), COUNT(*) " +
       "FROM call_llm_log c " +
       "WHERE YEAR(c.created_at) = :year " +
       "AND MONTH(c.created_at) = :month " +
       "GROUP BY DAY(c.created_at)", nativeQuery = true)
   List<Object[]> findCallLLMGroupByDay(@Param("year") int year,@Param("month") int month);
}
