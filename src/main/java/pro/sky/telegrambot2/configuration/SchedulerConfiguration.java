package pro.sky.telegrambot2.configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pro.sky.telegrambot2.entity.NotificationTask;
import pro.sky.telegrambot2.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class SchedulerConfiguration{
    @Autowired
    private NotificationTaskRepository taskRepository;
    @Autowired
    private TelegramBot telegramBot;


    @Scheduled(cron = "0 0/1 * * * *")
    public void run() {
        LocalDateTime dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        NotificationTask notificationTask = taskRepository.findByTime(dateTime);
        if (notificationTask != null) {
            SendMessage message = new SendMessage(notificationTask.getChatId(), notificationTask.getMessage());
            telegramBot.execute(message);
        }

    }

}
