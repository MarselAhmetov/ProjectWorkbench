package team404.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.enums.Currency;
import team404.project.model.entity.Debt;
import team404.project.model.enums.DebtStatus;
import team404.project.security.UserDetailsImpl;
import team404.project.model.dto.DebtDto;
import team404.project.service.interfaces.DebtService;
import team404.project.service.interfaces.TransactionService;
import team404.project.service.interfaces.UserService;

import java.time.LocalDate;

@Controller
public class DebtController {

    @Autowired
    DebtService debtService;
    @Autowired
    UserService userService;
    @Autowired
    TransactionService transactionService;

    @GetMapping("/debts")
    public ModelAndView getDebtsPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("debts", debtService.getByOwner(userDetails.getUser()));
        modelAndView.addObject("mydebts", debtService.getByDebtor(userDetails.getUser()));
        modelAndView.setViewName("pages/debts");
        return modelAndView;
    }

    @GetMapping("/debt")
    public ModelAndView getDebtPage(@RequestParam("id") Integer id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ModelAndView modelAndView = new ModelAndView("pages/debtPage");
        Debt debt = debtService.getById(id);
        modelAndView.addObject("debt", debt);
        modelAndView.addObject("transactions", transactionService.getAllByDebt(debt));
        return modelAndView;
    }

    @PostMapping("/debt")
    public ModelAndView archiveDebt(@RequestParam("debtId") Integer id, @RequestParam("action") String action) {
        ModelAndView modelAndView = new ModelAndView("redirect:/debts");
        switch (action) {
            case "archive":
                Debt debt = debtService.getById(id);
                debt.setStatus(DebtStatus.ARCHIVED);
                debtService.create(debt);
                break;
            case "delete":
                transactionService.deleteByDebt(debtService.getById(id));
                debtService.deleteById(id);
                break;
        }
        return modelAndView;
    }

    @PostMapping("/debts")
    public ModelAndView addDebt(@AuthenticationPrincipal UserDetailsImpl userDetails, DebtDto debtDto) {
        Debt debt = Debt.builder()
                .debtCount(debtDto.getDebtCount())
                .debtorName(debtDto.getDebtorName())
                .description(debtDto.getDescription())
                .currency(Currency.valueOf(debtDto.getCurrency()))
                .date(LocalDate.parse(debtDto.getDate()))
                .status(DebtStatus.OPENED)
                .build();

        if (debtDto.getWhos().equals("friend")) {
            debt.setDebtor(userService.getById(debtDto.getFriendId()));
            debt.setOwner(userDetails.getUser());
        } else {
            debt.setDebtor(userDetails.getUser());
            debt.setOwner(userService.getById(debtDto.getFriendId()));
        }
        debtService.create(debt);
        return new ModelAndView("redirect:/profile");
    }
}
