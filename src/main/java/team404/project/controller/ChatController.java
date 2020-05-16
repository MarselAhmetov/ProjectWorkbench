package team404.project.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.UserDetailsImpl;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public ModelAndView getChat(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        ModelAndView modelAndView = new ModelAndView("pages/chat");
        modelAndView.addObject("user", userDetails.getUser());
        return modelAndView;
    }
}
