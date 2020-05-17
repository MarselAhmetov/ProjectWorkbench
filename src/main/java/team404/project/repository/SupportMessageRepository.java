package team404.project.repository;

import org.springframework.data.repository.CrudRepository;
import team404.project.model.entity.SupportMessage;

public interface SupportMessageRepository extends CrudRepository<SupportMessage, Integer> {
}
