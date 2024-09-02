package com.beelinkers.englebee.general.controller.api;

import com.beelinkers.englebee.general.service.ChatService;
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
@RequestMapping("/api/chat")
public class ChatApiController {

  private final ChatService chatService;

  @GetMapping("/end-lecture")
  public ResponseEntity<String> endLecture(@RequestParam("lectureSeq") Long lectureSeq) {

    //lecture status finished로 변경하기 and lecture seq로 exam 만들기
    chatService.finishLectureCreateExam(lectureSeq);

    return ResponseEntity.ok().body("수업이 종료되었고 시험 생성이 완료되었습니다");
  }

}
