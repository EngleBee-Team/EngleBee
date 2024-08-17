package com.beelinkers.englebee.general.controller.api;

import com.beelinkers.englebee.general.service.LectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/general/lecture")
public class LectureApiController {

  private final LectureService lectureService;
}
