package com.beelinkers.englebee.general.llm;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/reply/get")
@RequiredArgsConstructor
public class GeneralGptTestController {

  private final GptQnaProxy gptQnaProxy;

  @GetMapping("/")
  public ResponseEntity<String> getReply() {
    return ResponseEntity.ok().body(gptQnaProxy.makeAutoReply("I am a boy가 왜 I am a boy야?"));
  }
}
