package team404.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import team404.project.scope.ErrorsCounter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @Autowired
    ErrorsCounter errorsCounter;

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {

        errorsCounter.handleNewError();
        System.out.println("Count of current errors handled: " + errorsCounter.getCounterOfHandledErrors() + " for session with id: " + request.getSession().getId());

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        ModelAndView modelAndView = new ModelAndView("pages/error");

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            modelAndView.addObject("statusCode", statusCode);
        }
        return modelAndView;
    }
}
