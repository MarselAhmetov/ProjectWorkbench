package team404.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import team404.project.model.MailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendMail(MailMessage mailMessage) {
        new Thread(() -> {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = null;
            try {
                helper = new MimeMessageHelper(mimeMessage, true);
                helper.setTo(mailMessage.getMailTo());
                helper.setSubject(mailMessage.getSubject());
                helper.setText(mailMessage.getText(), true);
            } catch (MessagingException e) {
                System.out.println("Error while sending message");
                throw new IllegalArgumentException(e);
            }
//        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
            javaMailSender.send(mimeMessage);
        }).start();
    }

}
