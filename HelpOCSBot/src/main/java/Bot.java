import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    private static final String TOKEN = "1641419779:AAF0dkBWIkQWvqau1JWGb8pfdLnR3i_kIL0";
    private static final String USERNAME = "HelpOCSBot";

    public Bot(DefaultBotOptions options) {super(options);}

    @Override
    public void onUpdateReceived(Update update) {
        if(update.getMessage() != null && update.getMessage().hasText()) {
            long chat_id = update.getMessage().getChatId();

            try {
                execute(new SendMessage(chat_id, "Привет " + update.getMessage().getText()));
            } catch (TelegramApiException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }
}
