package com.beelinkers.englebee.student.controller.api;

import com.beelinkers.englebee.general.domain.entity.ExamStatus;
import com.beelinkers.englebee.general.domain.entity.LectureStatus;
import com.beelinkers.englebee.student.dto.response.StudentMainPageLectureDTO;
import com.beelinkers.englebee.student.dto.response.StudentMainPageNewExamDTO;
import com.beelinkers.englebee.student.dto.response.StudentMainPageSubmitExamDTO;
import com.beelinkers.englebee.student.service.StudentMainService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student/main")
public class StudentMainApiController {

  private final StudentMainService studentMainService;

  @GetMapping("/lecture")
  public ResponseEntity<List<StudentMainPageLectureDTO>> getOngoingLecture(
      @RequestParam("memberSeq") Long memberSeq,
      @RequestParam("lectureSeq") Long lectureSeq,
      @RequestParam("lectureStatus") String lectureStatus) {
    LectureStatus status = LectureStatus.valueOf(lectureStatus.toUpperCase());
    List<StudentMainPageLectureDTO> lectureList = studentMainService.getOngoingLectureInfo(
        memberSeq,
        lectureSeq, status);
    return ResponseEntity.ok(lectureList);
  }

  @GetMapping("/created-exam")
  public ResponseEntity<List<StudentMainPageNewExamDTO>> getCreatedExam(
      @RequestParam("memberSeq") Long memberSeq) {
    ExamStatus status = ExamStatus.PREPARED;
    List<StudentMainPageNewExamDTO> createdExamList = studentMainService.getCreatedExamListInfo(
        memberSeq,
        status);
    return ResponseEntity.ok(createdExamList);
  }

  @GetMapping("/completed-exam")
  public ResponseEntity<List<StudentMainPageSubmitExamDTO>> getCompletedExam(
      @RequestParam("memberSeq") Long memberSeq,
      @RequestParam(value = "status", required = false) List<ExamStatus> status) {
    if (status == null || status.isEmpty()) {
      status = List.of(ExamStatus.SUBMITTED, ExamStatus.FEEDBACK_COMPLETED);
    }
    List<StudentMainPageSubmitExamDTO> completedExamList = studentMainService.getCompletedExamListInfo(
        memberSeq, status);
    return ResponseEntity.ok(completedExamList);
  }


}
