package team404.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.User;
import team404.project.service.UserService;

@Controller
public class FriendsController {

    @Autowired
    UserService userService;

    @GetMapping("/friends")
    public ModelAndView getFriendsPage() {
        ModelAndView modelAndView = new ModelAndView("friends");
        return modelAndView;
    }

    @PostMapping("/friends")
    public ModelAndView addFriend(@RequestParam String email, @AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView();
        User userFromDB;
        if ((userFromDB = userService.getUserByEmail(email)) != null && !user.getEmail().equals(email)) {
            // TODO: 28.03.2020 Отправить запрос дружбы
        } else if (userFromDB == null) {
            // TODO: 28.03.2020 Отправить приглашение на почту
        } else {
            modelAndView.setViewName("redirect:/friends?error");
            return modelAndView;
        }
        return modelAndView;
    }
}
