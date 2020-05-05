package team404.project.service.interfaces;

import team404.project.model.entity.Debt;

import java.util.List;

public interface DebtPrioritySorter {
    List<Debt> sortByPriority(List<Debt> debts);
}
