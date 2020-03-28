package team404.project.service;

import team404.project.model.MailMessage;

public interface MailSenderService {
    void sendMail(MailMessage mailMessage);
}
