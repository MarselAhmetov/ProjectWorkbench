package team404.project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import team404.project.model.entity.ChatMessage;
import team404.project.model.entity.User;

import java.util.List;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, Integer> {
    @Query("from ChatMessage m where m.sender = :user1 and m.receiver = :user2 or m.sender = :user2 and m.receiver = :user1")
    List<ChatMessage> getAllBySenderOrReceiver(User user1, User user2);
}
