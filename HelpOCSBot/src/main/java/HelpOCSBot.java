import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;

public class HelpOCSBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();

        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

        Bot bot = new Bot(botOptions);

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(bot);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
