package team404.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.Debt;
import team404.project.model.UserDetailsImpl;
import team404.project.model.dto.DebtDto;
import team404.project.service.interfaces.DebtService;
import team404.project.service.interfaces.UserService;

import java.util.List;

@Controller
public class DebtController {

    @Autowired
    DebtService debtService;
    @Autowired
    UserService userService;

    @GetMapping("/debts")
    public ModelAndView getDebtsPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        ModelAndView modelAndView = new ModelAndView();
        List<Debt> debts = debtService.getByOwner(userDetails.getUser());
        modelAndView.addObject("debts", debts);
        modelAndView.setViewName("debts");
        return modelAndView;
    }

    @PostMapping("/debts")
    public ModelAndView addDebt(@AuthenticationPrincipal UserDetailsImpl userDetails, DebtDto debtDto) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile");
        debtService.create(Debt.builder()
                .debtCount(debtDto.getDebtCount())
                .debtor(userService.getById(debtDto.getFriendId()))
                .owner(userDetails.getUser())
                .debtorName(debtDto.getDebtorName())
                .description(debtDto.getDescription())
                .build());
        return modelAndView;
    }
}
