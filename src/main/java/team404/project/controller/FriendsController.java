package team404.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.FriendRequest;
import team404.project.model.MailMessage;
import team404.project.model.User;
import team404.project.model.UserDetailsImpl;
import team404.project.model.dto.FriendRequestDto;
import team404.project.service.interfaces.FriendRequestService;
import team404.project.service.interfaces.MailSenderService;
import team404.project.service.interfaces.UserService;

import java.time.LocalDate;


@Controller
public class FriendsController {

    @Autowired
    UserService userService;
    @Autowired
    FriendRequestService friendRequestService;
    @Autowired
    MailSenderService mailSenderService;

    @GetMapping("/friends")
    public ModelAndView getFriendsPage() {
        ModelAndView modelAndView = new ModelAndView("friends");
        return modelAndView;
    }

    @PostMapping("/friends")
    public ModelAndView addFriend(FriendRequestDto friendRequestDto, @AuthenticationPrincipal UserDetailsImpl user) {
        ModelAndView modelAndView = new ModelAndView();
        User userFromDB;
        if ((userFromDB = userService.getUserByEmail(friendRequestDto.getEmail())) != null && !user.getUser().getEmail().equals(friendRequestDto.getEmail())) {
            friendRequestService.create(FriendRequest.builder()
                    .receiver(userService.getUserByEmail(friendRequestDto.getEmail()))
                    .sender(user.getUser())
                    .message(friendRequestDto.getMessage())
                    .date(LocalDate.now())
                    .build());
            // TODO: 28.03.2020 Отправить запрос дружбы
        } else if (userFromDB == null) {
            mailSenderService.sendMail(MailMessage.builder()
            .mailTo(friendRequestDto.getEmail())
            .subject("Invitation")
            .text("Your friend invites you in our service. His message: \n" + friendRequestDto.getMessage())
            .build());
            // TODO: 28.03.2020 Отправить приглашение на почту
        } else {
            modelAndView.setViewName("redirect:/friends?error");
            return modelAndView;
        }
        return modelAndView;
    }

    @PostMapping("/friendRequest")
    public ModelAndView friendRequest(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam("request") Integer requestId, @RequestParam("status") String status, @RequestParam("sender") Integer senderId) {
        ModelAndView modelAndView = new ModelAndView();
        User sender = userService.getById(senderId);
        sender.getFriends().add(userDetails.getUser());
        userDetails.getUser().getFriends().add(sender);
        userService.save(userDetails.getUser());
        userService.save(sender);
        friendRequestService.deleteById(requestId);
        modelAndView.setViewName("redirect:/profile");
        return modelAndView;
    }
}
