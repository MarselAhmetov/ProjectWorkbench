package team404.project.service.interfaces;

import team404.project.model.Debt;
import team404.project.model.User;

import java.util.List;

public interface DebtService {
    void create(Debt debt);
    void deleteById(Integer id);
    List<Debt> getByOwner(User owner);
}
