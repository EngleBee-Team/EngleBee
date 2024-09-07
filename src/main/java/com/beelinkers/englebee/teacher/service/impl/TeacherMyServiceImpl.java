package com.beelinkers.englebee.teacher.service.impl;

import com.beelinkers.englebee.auth.domain.repository.MemberRepository;
import com.beelinkers.englebee.general.domain.entity.Exam;
import com.beelinkers.englebee.general.domain.entity.ExamStatus;
import com.beelinkers.englebee.general.domain.entity.Question;
import com.beelinkers.englebee.general.domain.repository.ExamRepository;
import com.beelinkers.englebee.general.domain.repository.QuestionRepository;
import com.beelinkers.englebee.general.domain.repository.ReplyRepository;
import com.beelinkers.englebee.teacher.dto.response.TeacherMyPageExamHistoryDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMyPagePendingExamDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMyPageWrittenQnaDTO;
import com.beelinkers.englebee.teacher.dto.response.TeacherMyPageWrittenReplyDTO;
import com.beelinkers.englebee.teacher.dto.response.mapper.TeacherMyPageMapper;
import com.beelinkers.englebee.teacher.service.TeacherMyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TeacherMyServiceImpl implements TeacherMyService {

  private final TeacherMyPageMapper teacherMyPageMapper;
  private final ExamRepository examRepository;
  private final QuestionRepository questionRepository;
  private final MemberRepository memberRepository;
  private final ReplyRepository replyRepository;

  @Override
  @Transactional(readOnly = true)
  public Page<TeacherMyPagePendingExamDTO> getTeacherMyPendingExamInfo(Long memberSeq,
      Pageable pageable) {
    Page<Exam> pendingExamList = examRepository.findByLectureTeacherSeqAndStatus(
        memberSeq, ExamStatus.CREATED, pageable
    );
    return pendingExamList.map(teacherMyPageMapper::teacherMyPagePendingExam);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<TeacherMyPageExamHistoryDTO> getTeacherMyPageExamHistoryInfo(Long memberSeq,
      Pageable pageable) {
    Page<Exam> examHistoryList = examRepository.findByLectureTeacherSeqAndStatusNot(
        memberSeq, ExamStatus.CREATED, pageable
    );
    return examHistoryList.map(teacherMyPageMapper::teacherMyPageExamHistory);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<TeacherMyPageWrittenQnaDTO> getTeacherMyPageWrittenQnaInfo(Long memberSeq,
      Pageable pageable) {
    Page<Question> questionList = questionRepository.findByMemberSeqOrderByCreatedAtDesc(memberSeq,
        pageable);
    return questionList.map(teacherMyPageMapper::teacherMyPageWrittenQna);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<TeacherMyPageWrittenReplyDTO> getTeacherMyPageWrittenReplyInfo(Long memberSeq,
      Pageable pageable) {
    return replyRepository.findRepliesByMemberSeq(memberSeq, pageable)
        .map(teacherMyPageMapper::teacherMyPageWrittenReply);
  }

}