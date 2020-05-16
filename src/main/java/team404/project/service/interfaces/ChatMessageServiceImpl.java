package team404.project.service.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team404.project.model.entity.ChatMessage;
import team404.project.model.entity.User;
import team404.project.repository.ChatMessageRepository;

import java.util.List;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    ChatMessageRepository chatMessageRepository;

    @Override
    public List<ChatMessage> getAllBySenderOrReceiver(User sender, User receiver) {
        return chatMessageRepository.getAllBySenderOrReceiver(sender, receiver);
    }

    @Override
    public void save(ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
    }
}
