package team404.project.service;

import team404.project.model.User;

public interface UserService {
    User getUserByEmail(String email);
}
