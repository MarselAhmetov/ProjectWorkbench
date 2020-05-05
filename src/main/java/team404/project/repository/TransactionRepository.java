package team404.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team404.project.model.entity.Debt;
import team404.project.model.entity.Transaction;

import javax.transaction.Transactional;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> getAllByDebt(Debt debt);
    @Transactional
    void deleteAllByDebt(Debt debt);
}
