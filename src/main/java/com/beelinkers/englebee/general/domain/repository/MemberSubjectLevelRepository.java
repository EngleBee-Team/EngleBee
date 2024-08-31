package com.beelinkers.englebee.general.domain.repository;

import com.beelinkers.englebee.admin.dto.response.AdminMemberSubjectLevelCountDTO;
import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.general.domain.entity.MemberSubjectLevel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberSubjectLevelRepository extends JpaRepository<MemberSubjectLevel, Long> {

  @Query(
      "SELECT new com.beelinkers.englebee.admin.dto.response.AdminMemberSubjectLevelCountDTO(sl.levelCode, COUNT(msl)) "
          +
          "FROM MemberSubjectLevel msl " +
          "JOIN msl.subjectLevel sl " +
          "WHERE sl.subjectCode = 'GRAMMAR' " +
          "GROUP BY sl.levelCode")
  List<AdminMemberSubjectLevelCountDTO> findLevelCountsGrammer();

  @Query(
      "SELECT new com.beelinkers.englebee.admin.dto.response.AdminMemberSubjectLevelCountDTO(sl.levelCode, COUNT(msl)) "
          +
          "FROM MemberSubjectLevel msl " +
          "JOIN msl.subjectLevel sl " +
          "WHERE sl.subjectCode = 'SENTENCE' " +
          "GROUP BY sl.levelCode")
  List<AdminMemberSubjectLevelCountDTO> findLevelCountsSentence();

  @Query(
      "SELECT new com.beelinkers.englebee.admin.dto.response.AdminMemberSubjectLevelCountDTO(sl.levelCode, COUNT(msl)) "
          +
          "FROM MemberSubjectLevel msl " +
          "JOIN msl.subjectLevel sl " +
          "WHERE sl.subjectCode = 'WORD' " +
          "GROUP BY sl.levelCode")
  List<AdminMemberSubjectLevelCountDTO> findLevelCountsWord();

  @Query("SELECT msl FROM MemberSubjectLevel msl JOIN FETCH msl.subjectLevel WHERE msl.student = :student")
  List<MemberSubjectLevel> findByStudentWithSubjectLevel(@Param("student") Member student);
}
