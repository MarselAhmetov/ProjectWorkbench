package team404.project.service.implementations;

import org.springframework.stereotype.Service;
import team404.project.model.Debt;
import team404.project.service.interfaces.DebtPrioritySorter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DebtsPrioritySorterImpl implements DebtPrioritySorter {

    public List<Debt> sortByPriority(List<Debt> debts) {
        for (Debt debt : debts) {
            debt.setPriority(debt.getDebtCount() * 2.5 + Math.abs(ChronoUnit.DAYS.between(debt.getDate(), LocalDate.now())) * 15);
        }
        debts.sort((o1, o2) -> o2.getPriority().compareTo(o1.getPriority()));
        return debts;
    }
}
