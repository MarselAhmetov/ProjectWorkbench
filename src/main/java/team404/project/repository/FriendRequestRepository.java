package team404.project.repository;

import org.springframework.data.repository.CrudRepository;
import team404.project.model.entity.FriendRequest;
import team404.project.model.entity.User;

import java.util.List;

public interface FriendRequestRepository extends CrudRepository<FriendRequest, Integer> {
    List<FriendRequest> getByReceiver(User receiver);
    void deleteById(Integer id);
}
