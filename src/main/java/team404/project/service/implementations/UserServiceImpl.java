package team404.project.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import team404.project.model.entity.User;
import team404.project.repository.UserRepository;
import team404.project.service.interfaces.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getById(Integer id) {
        Optional<User> user;
        if ((user = userRepository.findById(id)).isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }
}
