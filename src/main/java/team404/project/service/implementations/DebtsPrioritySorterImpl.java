package team404.project.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team404.project.model.Debt;
import team404.project.service.interfaces.DebtPrioritySorter;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DebtsPrioritySorterImpl implements DebtPrioritySorter {
    @Autowired
    EntityManager entityManager;

    public List<Debt> sortByPriority(List<Debt> debts) {
        for (Debt debt : debts) {
            debt.setPriority(debt.getDebtCount() * 2.0 + Math.abs(ChronoUnit.DAYS.between(debt.getDate(), LocalDate.now())) * 100);
        }
        debts.sort((o1, o2) -> o2.getPriority().compareTo(o1.getPriority()));
        return debts;
    }
}
