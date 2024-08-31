package com.beelinkers.englebee.general.domain.repository;

import com.beelinkers.englebee.general.domain.entity.Lecture;
import com.beelinkers.englebee.general.domain.entity.LectureStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

  // 학생 수강 목록 조회
  @EntityGraph(attributePaths = {"subjectLevels.subjectLevel", "teacher"})
  Page<Lecture> findByStudentSeqAndStatus(Long studentSeq, LectureStatus status, Pageable pageable);

  // 선생님 강의 목록 조회
  Page<Lecture> findByTeacherSeqAndStatus(Long teacherSeq, LectureStatus status, Pageable pageable);
}

