package ru.ocs1c.help_ocs_bot.botconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import ru.ocs1c.help_ocs_bot.HelpOcsTelegramBot;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {

    /**
     * Поля - это параметры, которые указываются в application.properties -
     * имена полей в этом классе и параметров в файле application.properties должны совпадать
     */

    private String webHookPath;
    private String botUserName;
    private String botToken;

    @Bean
    public HelpOcsTelegramBot MyOcsBot() {
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);

//        options.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
//        options.setProxyHost("localhost");
//        options.setProxyPort(9150);


        // Создаем бота с настройками
        HelpOcsTelegramBot myOcsBot = new HelpOcsTelegramBot(options);
        myOcsBot.setBotUserName(botUserName);
        myOcsBot.setBotToken(botToken);
        myOcsBot.setWebHookPath(webHookPath);

        return  myOcsBot;
    }
}
