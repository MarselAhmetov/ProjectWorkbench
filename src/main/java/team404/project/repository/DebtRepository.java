package team404.project.repository;

import org.springframework.data.repository.CrudRepository;
import team404.project.model.Debt;
import team404.project.model.User;

import java.util.List;

public interface DebtRepository extends CrudRepository<Debt, Integer> {
    void deleteById(Integer id);
    List<Debt> getAllByOwner(User owner);
}
