package com.beelinkers.englebee.student.dto.response.mapper;

import com.beelinkers.englebee.student.dto.response.StudentExamSolvePageDTO;
import com.beelinkers.englebee.student.dto.response.TeacherQuestionForStudentToSolveDTO;
import com.beelinkers.englebee.teacher.domain.entity.TeacherQuestion;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StudentExamSolvePageMapper {

  public StudentExamSolvePageDTO toExamSolvePageDTO(String examTitle,
      List<TeacherQuestion> teacherQuestions) {
    List<TeacherQuestionForStudentToSolveDTO> teacherQuestionForStudentToSolveDTOs = teacherQuestions.stream()
        .map(tq -> new TeacherQuestionForStudentToSolveDTO(
            tq.getSeq(),
            tq.getDirection(),
            Arrays.stream(tq.getChoices().split(","))
                .map(String::trim)
                .toList()))
        .toList();
    return new StudentExamSolvePageDTO(examTitle, teacherQuestionForStudentToSolveDTOs);
  }

}
