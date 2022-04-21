package kz.nebula.daim;

import kz.botcs.InMessageHandler;
import kz.botcs.InMessageHandlerFactory;
import kz.botcs.telegram.TelegramChatbot;
import kz.botcs.telegram.dto.Update;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;

@SpringBootApplication
public class DaimApplication {

    @Bean
    public TelegramChatbot telegramChatbot() {
        return new TelegramChatbot(DaimClient.ID,
                "https://api.telegram.org/bot1712469362:AAGZ2ySMf1N8pE92vk0aDwGoOOTAZAO6hpQ");
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DaimApplication.class, args);

        TelegramChatbot telegramChatbot = context.getBean(TelegramChatbot.class);
        TaskExecutor taskExecutor = context.getBean(TaskExecutor.class);
        InMessageHandlerFactory factory = context.getBean(InMessageHandlerFactory.class);
        InMessageHandler<Update> handler = factory.createHandler(telegramChatbot);

        taskExecutor.execute(() -> {
            telegramChatbot.deleteWebhook();
            int offset = 0;
            //noinspection InfiniteLoopStatement
            while (true) {
                try {
                    for (Update update : telegramChatbot.getUpdates(offset)) {
                        try {
                            handler.handle(update);
                        } catch (RuntimeException e) {
                            e.printStackTrace();
                        }
                        offset = update.getUpdateId() + 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
