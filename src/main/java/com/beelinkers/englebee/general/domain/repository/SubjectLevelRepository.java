package com.beelinkers.englebee.general.domain.repository;

import com.beelinkers.englebee.general.domain.entity.LevelCode;
import com.beelinkers.englebee.general.domain.entity.SubjectCode;
import com.beelinkers.englebee.general.domain.entity.SubjectLevel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectLevelRepository extends JpaRepository<SubjectLevel, Long> {

  Optional<SubjectLevel> findBySubjectCodeAndLevelCode(SubjectCode subjectCode,
      LevelCode levelCode);
}
