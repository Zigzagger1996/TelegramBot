import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot implements Strings{
    public static void main(String[] args) {
        ApiContextInitializer.init(); // Инициализируем апи
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage(); // Это нам понадобится
        String txt = msg.getText();
        Article article = new Article();
        article.parseSite();
        if (txt.equals("/start")) {
            sendMsg(msg, Strings.WELLCOME_MESSAGGE);
        }
        if (txt.equals("/help")) {
            sendMsg(msg, Strings.HELP);
        }
        if (txt.equals("/internationalprizepool")) {
            sendMsg(msg, article.articleList.get(0));
        }
        if (txt.equals("/fuckyou")) {
            sendMsg(msg, Strings.FUCK_YOU);
        }
    }

    public String getBotUsername() {
        return "NewsKickerBot";
    }

    public String getBotToken() {
        return "575502942:AAEhEvIt16UZOIGt5PbBs-RSW4KOZ-pUVbg";
    }

    @SuppressWarnings("deprecation")
    private void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId()); // Боту может писать не один человек, и поэтому чтобы отправить сообщение, грубо говоря нужно узнать куда его отправлять
        s.setText(text);
        try { //Чтобы не крашнулась программа при вылете Exception
            sendMessage(s);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
