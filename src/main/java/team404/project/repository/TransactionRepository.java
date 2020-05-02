package team404.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team404.project.model.Debt;
import team404.project.model.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> getAllByDebt(Debt debt);
    void deleteAllByDebt(Debt debt);
}
