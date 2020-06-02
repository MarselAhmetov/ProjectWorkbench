package team404.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.MailMessage;
import team404.project.security.UserDetailsImpl;
import team404.project.model.dto.SupportMessageDto;
import team404.project.model.entity.SupportMessage;
import team404.project.model.entity.User;
import team404.project.model.enums.Role;
import team404.project.service.interfaces.MailSenderService;
import team404.project.service.interfaces.SupportMessageService;
import team404.project.service.interfaces.UserService;

import java.time.LocalDateTime;

@Controller
public class SupportController {

    @Autowired
    MailSenderService mailSenderService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    SupportMessageService supportMessageService;

    @GetMapping("/support")
    public ModelAndView getSupportPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        ModelAndView modelAndView = new ModelAndView("pages/support");
        if (userDetails != null) {
            modelAndView.addObject("user", userDetails.getUser());
        }
        return modelAndView;
    }

    @GetMapping("/support/messages")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPPORT')")
    public ModelAndView getSupportMessagesPage() {
        ModelAndView modelAndView = new ModelAndView("pages/supportMessages");
        modelAndView.addObject("messages", supportMessageService.findAll());
        return modelAndView;
    }


    @PostMapping("/support")
    public ModelAndView saveMessage(SupportMessageDto form) {
        supportMessageService.save(SupportMessage.builder()
                .dateTime(LocalDateTime.now())
                .subject(form.getSubject())
                .text(form.getText())
                .build());
        return new ModelAndView("redirect:/support");
    }

    @PostMapping("/support/new")
    public ModelAndView newSupport(@RequestParam("username") String username) {
        String code = passwordEncoder.encode(username);

        MailMessage mailMessage = MailMessage.builder()
                .mailTo("marsel5027@gmail.com")
                .subject("New Support")
                .text("New Support If you want to accept follow the link: " + "<a href='http://localhost:8080/support/confirm?username=" + username + "&code=" + code + "'>Confirm support</a>")
                .build();

        mailSenderService.sendMail(mailMessage);

        return new ModelAndView("redirect:/support");
    }

    @GetMapping("/support/confirm")
    public ModelAndView confirmSupport(@RequestParam("username") String username, @RequestParam("code") String code) {
        if (passwordEncoder.matches(username, code)) {
            User user = userService.getByUsername(username);
            user.setRole(Role.SUPPORT);
            userService.save(user);
        }
        return new ModelAndView("redirect:/support");
    }
}
