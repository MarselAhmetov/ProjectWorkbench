package team404.project.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team404.project.model.entity.Debt;
import team404.project.service.interfaces.DebtPrioritySorter;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DebtsPrioritySorterImpl implements DebtPrioritySorter {
    @Autowired
    EntityManager entityManager;

    @Autowired
    DebtServiceImpl debtService;

    @Autowired
    CurrencyService currencyService;

    public List<Debt> sortByPriority(List<Debt> debts) {
        for (Debt debt : debts) {
            debt.setPriority(debt.getDebtCount() * 2.0 * currencyService.getCurrencyToRuble(debt.getCurrency()) + Math.abs(ChronoUnit.DAYS.between(debt.getDate(), LocalDate.now())) * 200 * debtService.getByOwner(debt.getOwner()).size());
        }
        debts.sort((o1, o2) -> o2.getPriority().compareTo(o1.getPriority()));
        return debts;
    }
}
