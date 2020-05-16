package team404.project.service.interfaces;

import team404.project.model.entity.User;

import java.util.List;

public interface UserService {
    User getUserByEmail(String email);
    void save(User user);
    User getById(Integer id);
    List<User> getAll();
    User getByUsername(String username);
}
