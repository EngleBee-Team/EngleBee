package com.beelinkers.englebee.general.domain.repository;

import com.beelinkers.englebee.general.domain.entity.Lecture;
import com.beelinkers.englebee.general.domain.entity.LectureSubjectLevel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LectureSubjectLevelRepository extends JpaRepository<LectureSubjectLevel, Long> {

  @Query("SELECT lsl FROM LectureSubjectLevel lsl JOIN FETCH lsl.subjectLevel WHERE lsl.lecture = :lecture")
  List<LectureSubjectLevel> findByLectureWithSubjectLevel(@Param("lecture") Lecture lecture);
}
