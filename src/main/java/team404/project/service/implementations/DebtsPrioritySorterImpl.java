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
        // TODO: 03.04.2020 Исправить, сделать покрасивее
        Query query = entityManager.createNativeQuery("select owner_id, count(owner_id) from project.debt group by owner_id");
        List<Object[]> users = query.getResultList();
        Map<Integer, Long> debtCountMap = new HashMap<>();
        for (Object[] user : users) {
            debtCountMap.put((Integer) user[0], Long.parseLong(String.valueOf(user[1])));
        }
        System.out.println(debtCountMap);
        for (Debt debt : debts) {
            debt.setPriority(debt.getDebtCount() * 1.5 * debtCountMap.get(debt.getOwner().getId()) + Math.abs(ChronoUnit.DAYS.between(debt.getDate(), LocalDate.now())) * 100);
        }
        debts.sort((o1, o2) -> o2.getPriority().compareTo(o1.getPriority()));
        return debts;
    }
}
