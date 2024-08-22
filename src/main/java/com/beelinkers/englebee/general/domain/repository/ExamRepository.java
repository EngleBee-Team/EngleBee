package com.beelinkers.englebee.general.domain.repository;

import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.ExamStatus;
import com.beelinkers.englebee.general.domain.entity.LectureStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    Page<Exam> findByLectureStudentSeqAndStatus(Long memberSeq, ExamStatus status, Pageable pageable);
    Page<Exam> findByLectureStudentSeqAndStatusIn(Long memberSeq, List<ExamStatus> status, Pageable pageable);

    Page<Exam> findByLectureTeacherSeqAndStatus(Long lecture_teacher_seq, ExamStatus status, Pageable pageable);

}
