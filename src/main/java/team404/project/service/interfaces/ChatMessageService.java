package team404.project.service.interfaces;

import org.springframework.data.jpa.repository.Query;
import team404.project.model.entity.ChatMessage;
import team404.project.model.entity.User;

import java.util.List;

public interface ChatMessageService {
    List<ChatMessage> getAllBySenderOrReceiver(User user1, User user2);
    void save(ChatMessage chatMessage);
}
