package com.beelinkers.englebee.general.videochat;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
public class MyWebSocketHandler extends TextWebSocketHandler {

  private List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();

  //소켓 열림
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    log.info("afterConnectionEstablished"+session.getId());
    sessions.add(session);
  }
  // 전체 채팅
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    log.info("handleTextMessage "+session.getId()+" "+message.getPayload());
    for(WebSocketSession s : sessions){
      s.sendMessage(new TextMessage(session.getId()+" "+message.getPayload()));
    }
  }

  //소켓 닫음
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    sessions.remove(session);
    log.info("afterConnectionClosed"+session.getId());
  }
}
