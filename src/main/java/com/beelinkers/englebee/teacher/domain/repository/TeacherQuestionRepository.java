package com.beelinkers.englebee.teacher.domain.repository;

import com.beelinkers.englebee.auth.domain.entity.StudentGrade;
import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.SubjectLevel;
import com.beelinkers.englebee.teacher.domain.entity.TeacherQuestion;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherQuestionRepository extends JpaRepository<TeacherQuestion, Long> {

  @Query("SELECT tq FROM TeacherQuestion tq JOIN FETCH tq.exam e WHERE e = :exam")
  List<TeacherQuestion> findByExam(@Param("exam") Exam exam);

  @Query(value = """
      SELECT tq
      FROM TeacherQuestion tq
      JOIN tq.exam e
      JOIN e.lecture l
      JOIN l.subjectLevels sl
      WHERE sl.subjectLevel = :subjectLevel
        AND l.student.grade = :studentGrade
      ORDER BY RAND()
      """)
  List<TeacherQuestion> findRandomQuestionsBySubjectLevelAndStudentGrade(
      @Param("subjectLevel") SubjectLevel subjectLevel,
      @Param("studentGrade") StudentGrade studentGrade,
      Pageable pageable);
}
