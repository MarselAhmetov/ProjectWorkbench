package team404.project.repository;

import org.springframework.data.repository.CrudRepository;
import team404.project.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User getByEmail(String email);
    User getByUsername(String username);
}
