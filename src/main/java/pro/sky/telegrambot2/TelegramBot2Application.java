package pro.sky.telegrambot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TelegramBot2Application {

	public static void main(String[] args) {
		SpringApplication.run(TelegramBot2Application.class, args);
	}

}
