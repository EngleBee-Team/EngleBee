package com.beelinkers.englebee.auth.domain.repository;

import com.beelinkers.englebee.admin.dto.response.AdminMemberAgeGroupCountDTO;
import com.beelinkers.englebee.auth.domain.entity.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {


  @Query("SELECT new com.beelinkers.englebee.admin.dto.response.AdminMemberAgeGroupCountDTO(m.grade, COUNT(m)) " +
      "FROM Member m " +
      "GROUP BY m.grade")
  List<AdminMemberAgeGroupCountDTO> findAgeGroupCount();

  Optional<Member> findByEmail(String email);

  Optional<Member> findByNickname(String nickname);

}
