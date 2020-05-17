package team404.project.service.interfaces;

import team404.project.model.entity.SupportMessage;

import java.util.List;

public interface SupportMessageService {
    List<SupportMessage> findAll();
    void save(SupportMessage supportMessage);
}
