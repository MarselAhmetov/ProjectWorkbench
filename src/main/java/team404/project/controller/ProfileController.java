package team404.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.Debt;
import team404.project.model.UserDetailsImpl;
import team404.project.repository.FriendRequestRepository;
import team404.project.service.implementations.DebtsPrioritySorterImpl;
import team404.project.service.interfaces.DebtPrioritySorter;
import team404.project.service.interfaces.DebtService;
import team404.project.service.interfaces.UserService;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    UserService userService;
    @Autowired
    FriendRequestRepository friendRequestRepository;
    @Autowired
    DebtService debtService;
    @Autowired
    DebtPrioritySorter debtPrioritySorter;

    @GetMapping("/profile")
    public ModelAndView getProfile(@AuthenticationPrincipal UserDetailsImpl user) {
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("user", userService.getById(user.getUser().getId()));
        modelAndView.addObject("friendRequests", friendRequestRepository.getByReceiver(((UserDetailsImpl) user).getUser()));
        List<Debt> debts = debtPrioritySorter.sortByPriority(debtService.getByDebtor(user.getUser()));
        modelAndView.addObject("priorityDebts", debts.subList(0, Math.min(debts.size(), 10)));
        return modelAndView;
    }
}
