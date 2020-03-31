package team404.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.UserDetailsImpl;
import team404.project.repository.FriendRequestRepository;
import team404.project.service.interfaces.UserService;

@Controller
public class ProfileController {
    @Autowired
    UserService userService;

    @Autowired
    FriendRequestRepository friendRequestRepository;

    @GetMapping("/profile")
    public ModelAndView getProfile(@AuthenticationPrincipal UserDetailsImpl user) {
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("user", userService.getById(user.getUser().getId()));
        modelAndView.addObject("friendRequests", friendRequestRepository.getByReceiver(((UserDetailsImpl) user).getUser()));
        return modelAndView;
    }
}
