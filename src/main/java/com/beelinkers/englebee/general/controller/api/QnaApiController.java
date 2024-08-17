package com.beelinkers.englebee.general.controller.api;

import com.beelinkers.englebee.general.service.QnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/general/qna")
@RequiredArgsConstructor
public class QnaApiController {

  private final QnaService qnaService;
}
