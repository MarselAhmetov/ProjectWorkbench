package team404.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;
import team404.project.service.interfaces.UserService;

@Controller
@RequestScope
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("/admin")
    public ModelAndView getAdminPage() {
        ModelAndView modelAndView = new ModelAndView("pages/admin");
        modelAndView.addObject("users", userService.getAll());
        return modelAndView;
    }
}
