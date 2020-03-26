package team404.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team404.project.model.ConfirmCode;
import team404.project.model.Status;
import team404.project.model.User;
import team404.project.repository.ConfirmCodeRepository;
import team404.project.repository.UserRepository;

@Service
public class ConfirmServiceImpl implements ConfirmService {

    @Autowired
    ConfirmCodeRepository confirmCodeRepository;
    @Autowired
    UserRepository userRepository;

    public void confirm(String code) {
        ConfirmCode confirmCode = confirmCodeRepository.getByCode(code);
        User user;
        if (confirmCode != null) {
            user = confirmCode.getUser();
            user.setStatus(Status.CONFIRMED);
            userRepository.save(user);
            confirmCodeRepository.delete(confirmCode);
        }
    }
}
