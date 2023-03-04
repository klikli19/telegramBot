package pro.sky.telegrambot2.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot2.entity.NotificationTask;
import pro.sky.telegrambot2.repository.NotificationTaskRepository;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private NotificationTaskRepository taskRepository;
    

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(this::accept);
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void accept(Update update) {
        logger.info("Processing update: {}", update);
        SendMessage greeting = new SendMessage(update.message().chat().id(), "Хай, пипл");
        String textMessage = update.message().text();

        if (textMessage != null && update.message().text().equals("/start")) {
            telegramBot.execute(greeting);
        } else {
            Pattern pattern = Pattern.compile("([0-9.\\:\\s]{16})(\\s)([\\W+]+)");
            NotificationTask notificationTask = new NotificationTask();
            assert textMessage != null;
            Matcher matcher = pattern.matcher(textMessage);
            if (matcher.matches()) {
                notificationTask.setChatId((update.message().chat().id()));
                notificationTask.setTimeMessage(Timestamp.valueOf(LocalDateTime.parse(matcher.group(1),
                        DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))).toLocalDateTime());
                notificationTask.setMessage(matcher.group(3));
                taskRepository.save(notificationTask);
            }
        }
    }
}
