package team404.project.service;

import team404.project.model.ConfirmCode;

public interface ConfirmService {
    void confirm(String confirmCode);
}
