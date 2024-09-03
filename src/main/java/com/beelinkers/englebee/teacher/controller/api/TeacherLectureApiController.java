package com.beelinkers.englebee.teacher.controller.api;


import com.beelinkers.englebee.general.service.LectureService;
import com.beelinkers.englebee.teacher.dto.request.TeacherRegisterLectureRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher/lecture")
public class TeacherLectureApiController {

  private final LectureService lectureService;

  @PostMapping("/register")
  public void registerLecture(@RequestBody TeacherRegisterLectureRequestDTO teacherRegisterLectureRequestDTO) {
    //시큐리티 적용 필요
    Long teacherSeq = 1L;
    lectureService.createLecture(teacherSeq,teacherRegisterLectureRequestDTO);
  }

}
