package com.beelinkers.englebee.general.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.Lecture;
import com.beelinkers.englebee.general.domain.repository.ExamRepository;
import com.beelinkers.englebee.general.domain.repository.LectureRepository;
import com.beelinkers.englebee.general.service.impl.ChatServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
public class ChatServiceImplTest {

  @Mock
  private LectureRepository lectureRepository;

  @Mock
  private ExamRepository examRepository;

  @InjectMocks
  private ChatServiceImpl chatService;

  @Test
  @DisplayName("Lecture 종료시 LectureSeq를 이용하여 Lecture의 상태를 finished로 전환하고 Exam이 새로 저장됩니다")
  void 수업이_종료되면_시험이_새로_열립니다(){
    //given
    Long lectureSeq = 1L;
    Lecture mokeLecture = mock(Lecture.class);
    when(lectureRepository.findById(lectureSeq)).thenReturn(java.util.Optional.of(mokeLecture));

    //when
    chatService.finishLectureCreateExam(lectureSeq);

    //then
    verify(mokeLecture).finish();

    ArgumentCaptor<Exam> examCapter = ArgumentCaptor.forClass(Exam.class);
    verify(examRepository).save(examCapter.capture());

    Exam createdExam = examCapter.getValue();
    assertNotNull(createdExam);
    assertEquals(mokeLecture, createdExam.getLecture());

  }

}
