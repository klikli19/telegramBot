package pro.sky.telegrambot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot2.entity.NotificationTask;

public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Long> {
}
