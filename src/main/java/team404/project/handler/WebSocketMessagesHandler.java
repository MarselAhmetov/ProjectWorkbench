package team404.project.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import team404.project.model.Message;
import team404.project.model.entity.ChatMessage;
import team404.project.service.interfaces.ChatMessageService;
import team404.project.service.interfaces.UserService;

import java.util.Map;

@Component
@EnableWebSocket
public class WebSocketMessagesHandler extends TextWebSocketHandler {

    @Autowired
    private Map<String, WebSocketSession> sessions;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    UserService userService;

    @Autowired
    ChatMessageService chatMessageService;

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String messageText = (String) message.getPayload();
        Message messageFromJson = objectMapper.readValue(messageText, Message.class);
        if (!sessions.containsKey(messageFromJson.getSender())) {
            sessions.put(messageFromJson.getSender(), session);
        } else {
            if (!sessions.get(messageFromJson.getSender()).getId().equals(session.getId())) {
                sessions.put(messageFromJson.getSender(), session);
            }
        }

        if (messageFromJson.getReceiver() != null) {
            chatMessageService.save(ChatMessage.builder()
                    .sender(userService.getByUsername(messageFromJson.getSender()))
                    .receiver(userService.getByUsername(messageFromJson.getReceiver()))
                    .text(messageFromJson.getText())
                    .build());
            session.sendMessage(message);
        }

        if (sessions.get(messageFromJson.getReceiver()) != null) {
            try {
                System.out.println(sessions);
                sessions.get(messageFromJson.getReceiver()).sendMessage(message);
            } catch (Exception e) {
                synchronized (sessions) {
                    sessions.remove(messageFromJson.getReceiver());
                }
            }
        }
    }
}
