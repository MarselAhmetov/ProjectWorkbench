package team404.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.dto.SignUpDto;
import team404.project.service.interfaces.SignUpService;

import javax.validation.Valid;

@Controller
public class SignUpController {

    @Autowired
    SignUpService signUpService;

    @GetMapping("/signUp")
    public ModelAndView getSignUpPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("signUpDto", new SignUpDto());
        modelAndView.setViewName("pages/signup");
        return modelAndView;
    }

    @PostMapping("/signUp")
    public ModelAndView signUp(@Valid SignUpDto signUpDto, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(bindingResult.getAllErrors());
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("error", bindingResult.getAllErrors());
            modelAndView.addObject("signUpDto", new SignUpDto());
            modelAndView.setViewName("pages/signup");
        } else {
            signUpService.signUp(signUpDto);
            modelAndView.setViewName("redirect:/signIn");
        }
        return modelAndView;
    }
}
