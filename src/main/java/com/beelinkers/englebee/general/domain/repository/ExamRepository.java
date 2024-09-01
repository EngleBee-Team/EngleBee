package com.beelinkers.englebee.general.domain.repository;

import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.ExamStatus;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//findTop5ByStudentSeqAndStatusOrderByCreatedAtDesc
public interface ExamRepository extends JpaRepository<Exam, Long> {

  // student : main page
  List<Exam> findTop5ByLectureStudentSeqAndStatusOrderByCreatedAtDesc(Long studentSeq,
      ExamStatus status);

  List<Exam> findTop5ByLectureStudentSeqAndStatusInOrderByCreatedAtDesc(Long memberSeq,
      List<ExamStatus> status);

  // student : my page
  Page<Exam> findByLectureStudentSeqAndStatus(Long memberSeq, ExamStatus status, Pageable pageable);

  Page<Exam> findByLectureStudentSeqAndStatusIn(Long memberSeq, List<ExamStatus> status,
      Pageable pageable);

  // teacher : main Page
  List<Exam> findTop5ByLectureTeacherSeqAndStatusOrderByCreatedAtDesc(Long studentSeq,
      ExamStatus status);

  List<Exam> findTop5ByLectureTeacherSeqAndStatusInOrderByCreatedAtDesc(Long memberSeq,
      List<ExamStatus> status);

  // teacher : my page
  Page<Exam> findByLectureTeacherSeqAndStatus(Long memberSeq, ExamStatus status, Pageable pageable);

  Page<Exam> findByLectureTeacherSeqAndStatusNot(Long memberSeq, ExamStatus status,
      Pageable pageable);
}
