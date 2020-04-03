package team404.project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import team404.project.model.Debt;
import team404.project.model.User;

import java.util.List;

public interface DebtRepository extends CrudRepository<Debt, Integer> {
    void deleteById(Integer id);
    List<Debt> getAllByOwner(User owner);
    List<Debt> getAllByDebtor(User debtor);
//    Long countAllByOwner(User owner);
    @Query(value = "select * from project.debt where debtor_id = ?1 order by debt_count asc limit ?2", nativeQuery = true)
    List<Debt> findMinDebtCount(Integer id, Integer limit);
    @Query(value = "select * from project.debt where debtor_id = ?1 order by debt_count desc limit ?2", nativeQuery = true)
    List<Debt> findMaxDebtCount(Integer id, Integer limit);
    @Query(value = "select * from project.debt where debtor_id = ?1 order by date limit ?2", nativeQuery = true)
    List<Debt> findOldestDebt(Integer id, Integer limit);
}
