package team404.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.entity.FriendRequest;
import team404.project.model.MailMessage;
import team404.project.model.entity.User;
import team404.project.security.UserDetailsImpl;
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
    public ModelAndView getFriendsPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        ModelAndView modelAndView = new ModelAndView("pages/friends");
        modelAndView.addObject("friends", userService.getById(userDetails.getUser().getId()).getFriends());
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
        } else if (userFromDB == null) {     
            mailSenderService.sendMail(MailMessage.builder()
            .mailTo(friendRequestDto.getEmail())
            .subject("Invitation")
            .text("Your friend invites you in our service. His message: \n" + friendRequestDto.getMessage())
            .build());
        } else {
            modelAndView.setViewName("redirect:/friends?error");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/friends");
        return modelAndView;
    }

    @PostMapping("/friendRequest")
    public ModelAndView friendRequest(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam("request") Integer requestId, @RequestParam("status") String status, @RequestParam("sender") Integer senderId) {
        ModelAndView modelAndView = new ModelAndView();

        if(status.equals("accept")) {
            User sender = userService.getById(senderId);
            sender.getFriends().add(userDetails.getUser());

            User currentUser = userService.getById(userDetails.getUser().getId());
            currentUser.getFriends().add(sender);

            userService.save(currentUser);
            userService.save(sender);
        }
        friendRequestService.deleteById(requestId);
        modelAndView.setViewName("redirect:/profile");
        return modelAndView;
    }
}
