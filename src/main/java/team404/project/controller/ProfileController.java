package team404.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.entity.Debt;
import team404.project.security.UserDetailsImpl;
import team404.project.model.entity.User;
import team404.project.service.interfaces.*;

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
    @Autowired
    ChatMessageService chatMessageService;

    @GetMapping("/profile")
    public ModelAndView getProfile(@AuthenticationPrincipal UserDetailsImpl user) {
        ModelAndView modelAndView = new ModelAndView("pages/profile");
        modelAndView.addObject("currentUser", userService.getById(user.getUser().getId()));
        modelAndView.addObject("friendRequests", friendRequestService.getByReceiver(user.getUser()));
        modelAndView.addObject("oldestDebts", debtService.findOldestDebtByDebtor(user.getUser(), 3));
        modelAndView.addObject("maxDebtCounts", debtService.findMaxDebtCountByDebtor(user.getUser(), 5));
        modelAndView.addObject("minDebtCounts", debtService.findMinDebtCountByDebtor(user.getUser(), 3));
        List<Debt> debts = debtPrioritySorter.sortByPriority(debtService.getByDebtor(user.getUser()));
        modelAndView.addObject("priorityDebts", debts.subList(0, Math.min(debts.size(), 13)));
        return modelAndView;
    }

    @GetMapping("/profile/{userId}")
    public ModelAndView getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("userId") Integer userId) {
        ModelAndView modelAndView = new ModelAndView("pages/userProfile");
        User user = userService.getById(userId);
        modelAndView.addObject("user", user);
        modelAndView.addObject("currentUser", userDetails.getUser());
        modelAndView.addObject("friendDebts", debtService.getAllByDebtorAndOwner(user, userDetails.getUser()));
        modelAndView.addObject("myDebts", debtService.getAllByDebtorAndOwner(userDetails.getUser(), user));
        modelAndView.addObject("messages", chatMessageService.getAllBySenderOrReceiver(user, userDetails.getUser()));
        return modelAndView;
    }
}
