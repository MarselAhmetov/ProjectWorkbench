package team404.project.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team404.project.model.entity.Debt;
import team404.project.model.entity.User;
import team404.project.repository.DebtRepository;
import team404.project.service.interfaces.DebtService;

import java.util.List;

@Service
public class DebtServiceImpl implements DebtService {

    @Autowired
    DebtRepository debtRepository;

    @Override
    public void create(Debt debt) {
        debtRepository.save(debt);
    }

    @Override
    public void deleteById(Integer id) {
        debtRepository.deleteById(id);
    }

    @Override
    public List<Debt> getByOwner(User owner) {
        return debtRepository.getAllByOwner(owner);
    }

    @Override
    public List<Debt> getByDebtor(User debtor) {
        return debtRepository.getAllByDebtor(debtor);
    }

    @Override
    public List<Debt> getAllByDebtorAndOwner(User debtor, User owner) {
        return debtRepository.getAllByDebtorAndOwner(debtor, owner);
    }

    @Override
    public List<Debt> findMinDebtCountByDebtor(User user, Integer limit) {
        return debtRepository.findMinDebtCount(user.getId(), limit);
    }

    @Override
    public List<Debt> findMaxDebtCountByDebtor(User user, Integer limit) {
        return debtRepository.findMaxDebtCount(user.getId(), limit);
    }

    public List<Debt> findOldestDebtByDebtor(User user, Integer limit) {
        return debtRepository.findOldestDebt(user.getId(), limit);
    }

    @Override
    public Debt getById(Integer id) {
        return debtRepository.getById(id);
    }
}
