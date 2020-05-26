package team404.project.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team404.project.model.entity.SupportMessage;
import team404.project.repository.SupportMessageRepository;
import team404.project.service.interfaces.SupportMessageService;

import java.util.List;

@Service
public class SupportMessageServiceImpl implements SupportMessageService {

    @Autowired
    SupportMessageRepository supportMessageRepository;

    @Override
    public List<SupportMessage> findAll() {
        return (List<SupportMessage>) supportMessageRepository.findAll();
    }

    @Override
    public void save(SupportMessage supportMessage) {
        supportMessageRepository.save(supportMessage);
    }
}
