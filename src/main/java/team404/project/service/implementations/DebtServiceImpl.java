package team404.project.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team404.project.model.Debt;
import team404.project.model.User;
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
}
