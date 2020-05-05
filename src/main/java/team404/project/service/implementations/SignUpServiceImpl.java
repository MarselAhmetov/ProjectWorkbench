package team404.project.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team404.project.model.*;
import team404.project.model.dto.SignUpDto;
import team404.project.model.entity.ConfirmCode;
import team404.project.model.entity.User;
import team404.project.model.enums.AccountStatus;
import team404.project.model.enums.Role;
import team404.project.repository.ConfirmCodeRepository;
import team404.project.repository.UserRepository;
import team404.project.service.interfaces.MailSenderService;
import team404.project.service.interfaces.SignUpService;

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
                .accountStatus(AccountStatus.NOT_CONFIRMED)
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
