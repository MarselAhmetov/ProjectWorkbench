package team404.project.service.interfaces;

import team404.project.model.entity.Debt;
import team404.project.model.entity.Transaction;

import java.util.List;

public interface TransactionService {
    void save(Transaction transaction);
    List<Transaction> getAllByDebt(Debt debt);
    void deleteByDebt(Debt debt);
}
