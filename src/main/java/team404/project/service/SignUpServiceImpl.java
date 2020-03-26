package team404.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team404.project.model.*;
import team404.project.repository.ConfirmCodeRepository;
import team404.project.repository.UserRepository;

import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ConfirmCodeRepository confirmCodeRepository;
    @Autowired
    MailSenderService mailSenderService;

    public void signUp(SignUpDto signUpDto) {
        User user = User.builder()
                .email(signUpDto.getEmail())
                .username(signUpDto.getUsername())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .role(Role.USER)
                .status(Status.NOT_CONFIRMED)
                .build();
        userRepository.save(user);

        ConfirmCode confirmCode = ConfirmCode.builder()
                .user(user)
                .code(UUID.randomUUID().toString())
                .build();
        confirmCodeRepository.save(confirmCode);

        MailMessage mailMessage = MailMessage.builder()
                .mailTo(user.getEmail())
                .subject("Account confirmation")
                .text("<a href='http://localhost:8080/confirm?code=" + confirmCode.getCode() + "'>Confirm account</a>")
                .build();
        mailSenderService.sendMail(mailMessage);
    }
}
