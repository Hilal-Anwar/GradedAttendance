package org.graded_classes.graded_attendance.messaging;

import org.graded_classes.graded_attendance.data.MessageData;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Bot extends TelegramLongPollingBot {
    static MessageData messageData = new MessageData();

    @Override
    public String getBotUsername() {
        System.out.println("Testing file");
        return " ";
    }

    @Override
    public String getBotToken() {
        return " ";
    }

    @Override
    public void onUpdateReceived(Update update) {
        int a=10;
        var msg = update.getMessage();
        var user = msg.getFrom();
        System.out.println(user.getFirstName());
        System.out.println(user.getId());
        System.out.println(msg.getText());
        var x = msg.getText().split(",");
        messageData.add(user.getId(), user.getFirstName(), x[0], x[1]);
    }

    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        Bot bot = new Bot();
        //We moved this line out of the register method, to access it later
        botsApi.registerBot(bot);
        for (var x : messageData.getStudentIds()) {
            bot.sendText(x, "how are you");
            bot.sendText(x, "hope your are doing well");
        }

    }

}