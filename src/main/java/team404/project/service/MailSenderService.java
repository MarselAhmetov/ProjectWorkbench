package team404.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import team404.project.model.MailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailSenderService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendMail(MailMessage mailMessage) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(mailMessage.getMailTo());
            helper.setSubject(mailMessage.getSubject());
            helper.setText(mailMessage.getText(), true);
        } catch (MessagingException e) {

        }
//        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
        javaMailSender.send(mimeMessage);
    }

}
