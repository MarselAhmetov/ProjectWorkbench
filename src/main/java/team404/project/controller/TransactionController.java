package team404.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.Debt;
import team404.project.model.Transaction;
import team404.project.model.UserDetailsImpl;
import team404.project.model.dto.TransactionDto;
import team404.project.service.interfaces.DebtService;
import team404.project.service.interfaces.TransactionService;

import java.time.LocalDateTime;

@Controller
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    DebtService debtService;

    @PostMapping("/transaction")
    public ModelAndView newTransaction(@AuthenticationPrincipal UserDetailsImpl userDetails, TransactionDto transactionDto) {
        ModelAndView modelAndView = new ModelAndView("redirect:/debts");
        Debt debt = debtService.getById(transactionDto.getDebtId());
        debt.setDebtCount(debt.getDebtCount() - transactionDto.getCount());
        Transaction transaction = Transaction.builder()
                .actioner(userDetails.getUser())
                .count(transactionDto.getCount())
                .debt(debt)
                .time(LocalDateTime.now())
                .build();
        transactionService.save(transaction);
        return modelAndView;
    }
}
