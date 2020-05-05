package team404.project.service.interfaces;

import team404.project.model.entity.FriendRequest;
import team404.project.model.entity.User;

import java.util.List;

public interface FriendRequestService {
    List<FriendRequest> getByReceiver(User user);
    void create(FriendRequest friendRequest);
    void deleteById(Integer id);
}
