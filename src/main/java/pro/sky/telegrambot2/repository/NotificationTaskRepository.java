package pro.sky.telegrambot2.repository;

import org.hibernate.query.criteria.internal.expression.function.CurrentTimeFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot2.entity.NotificationTask;

import java.time.LocalDateTime;

@Repository
public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Integer> {
    NotificationTask findByTime(LocalDateTime dateTime);
}
