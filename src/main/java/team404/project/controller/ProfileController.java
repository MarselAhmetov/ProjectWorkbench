package team404.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.entity.Debt;
import team404.project.model.UserDetailsImpl;
import team404.project.service.interfaces.DebtPrioritySorter;
import team404.project.service.interfaces.DebtService;
import team404.project.service.interfaces.FriendRequestService;
import team404.project.service.interfaces.UserService;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    UserService userService;
    @Autowired
    FriendRequestService friendRequestService;
    @Autowired
    DebtService debtService;
    @Autowired
    DebtPrioritySorter debtPrioritySorter;

    @GetMapping("/profile")
    public ModelAndView getProfile(@AuthenticationPrincipal UserDetailsImpl user) {
        ModelAndView modelAndView = new ModelAndView("pages/profile");
        modelAndView.addObject("user", userService.getById(user.getUser().getId()));
        modelAndView.addObject("friendRequests", friendRequestService.getByReceiver(user.getUser()));
        modelAndView.addObject("oldestDebts", debtService.findOldestDebtByDebtor(user.getUser(), 3));
        modelAndView.addObject("maxDebtCounts", debtService.findMaxDebtCountByDebtor(user.getUser(), 3));
        modelAndView.addObject("minDebtCounts", debtService.findMinDebtCountByDebtor(user.getUser(), 3));
        List<Debt> debts = debtPrioritySorter.sortByPriority(debtService.getByDebtor(user.getUser()));
        modelAndView.addObject("priorityDebts", debts.subList(0, Math.min(debts.size(), 13)));
        return modelAndView;
    }
}
