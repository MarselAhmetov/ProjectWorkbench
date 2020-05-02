package team404.project.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team404.project.model.Debt;
import team404.project.model.Transaction;
import team404.project.repository.TransactionRepository;
import team404.project.service.interfaces.TransactionService;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllByDebt(Debt debt) {
        return transactionRepository.getAllByDebt(debt);
    }

    @Override
    public void deleteByDebt(Debt debt) {
        transactionRepository.deleteAllByDebt(debt);
    }
}
