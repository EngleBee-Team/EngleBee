package com.beelinkers.englebee.general.videochat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
  private final ObjectMapper objectMapper = new ObjectMapper();

  //소켓 열림
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    log.info("afterConnectionEstablished"+session.getId());
    sessions.add(session);
  }
  // 전체 채팅
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    log.info("handleTextMessage "+session.getId()+" "+message.getPayload());

    // 메시지의 Payload를 JSON으로 파싱
    String payload = message.getPayload();
    JsonNode jsonNode = objectMapper.readTree(payload);

    // "event" 필드의 값을 추출
    String eventValue = jsonNode.get("event").asText();

    if(eventValue.equals("chat")) {
      for(WebSocketSession s : sessions){
        String modifiedMessage = null;
        if (s.getId().equals(session.getId())) {
          modifiedMessage = "나: " + jsonNode.get("data").asText()+" ";
        }else{
          modifiedMessage = "상대방: " + jsonNode.get("data").asText()+" ";
        }

        ObjectNode newMessage = objectMapper.createObjectNode();
        newMessage.put("event", "chat");
        newMessage.put("data", modifiedMessage);

        String jsonString = objectMapper.writeValueAsString(newMessage);
        s.sendMessage(new TextMessage(jsonString));
      }
    }else {
      for (WebSocketSession s : sessions) {
        if (!s.getId().equals(session.getId())) {
          s.sendMessage(new TextMessage(message.getPayload()));
        }// if
      } // for
    }//else
  }

  //소켓 닫음
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    sessions.remove(session);
    log.info("afterConnectionClosed"+session.getId());
  }
}
