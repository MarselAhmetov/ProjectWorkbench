package team404.project.controller;

import bell.oauth.discord.main.OAuthBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.MailMessage;
import team404.project.security.UserDetailsImpl;
import team404.project.model.entity.User;
import team404.project.model.enums.AccountStatus;
import team404.project.model.enums.Role;
import team404.project.service.interfaces.MailSenderService;
import team404.project.service.interfaces.UserService;

import java.io.IOException;
import java.util.UUID;

@Controller
public class DiscordController {

    @Autowired
    private OAuthBuilder builder;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSenderService mailSenderService;


    @GetMapping("/discord/{type}")
    public ModelAndView getDiscordAuth(@RequestParam("code") String code, @PathVariable("type") String type) throws IOException {
        builder.setRedirectURI("http://localhost:8080/discord/" + type);
        builder.exchange(code);

        if (type.equals("register")) {
            bell.oauth.discord.domain.User user = builder.getUser();
            String password = UUID.randomUUID().toString();

            if (userService.getUserByEmail(user.getEmail()) == null) {
                MailMessage mailMessage = MailMessage.builder()
                        .mailTo(user.getEmail())
                        .subject("Account registration")
                        .text("Your autogenerated password " + password + "<br> You can change your password in your profile")
                        .build();

                mailSenderService.sendMail(mailMessage);
                User newUser = User.builder()
                        .email(user.getEmail())
                        .username(user.getUsername())
                        .accountStatus(AccountStatus.CONFIRMED)
                        .role(Role.USER)
                        .password(passwordEncoder.encode(password))
                        .build();
                userService.save(newUser);
            }
        }

        User user = userService.getUserByEmail(builder.getUser().getEmail());
        if (user != null) {
            UserDetailsImpl userDetails = new UserDetailsImpl(user);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);
            return new ModelAndView("redirect:/profile");
        }

        return new ModelAndView("redirect:/signIn");
    }
}
