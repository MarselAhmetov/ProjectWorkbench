package team404.project.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team404.project.model.entity.ConfirmCode;
import team404.project.model.enums.AccountStatus;
import team404.project.model.entity.User;
import team404.project.repository.ConfirmCodeRepository;
import team404.project.repository.UserRepository;
import team404.project.service.interfaces.ConfirmService;

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
            user.setAccountStatus(AccountStatus.CONFIRMED);
            userRepository.save(user);
            confirmCodeRepository.delete(confirmCode);
        }
    }
}
