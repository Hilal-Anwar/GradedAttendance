package org.graded_classes.graded_attendance.data;

import org.graded_classes.graded_attendance.controller.MainController;
import org.graded_classes.graded_attendance.messaging.TelegramBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class MessageSender {
    MessageData message;

    public MessageSender(DatabaseLoader databaseLoader, MainController mainController) {
        message = new MessageData(databaseLoader,mainController);
        TelegramBotsApi botsApi;
        try {
            botsApi = new TelegramBotsApi(DefaultBotSession.class);
            TelegramBot bot = new TelegramBot();
            bot.setMessageData(message);
            botsApi.registerBot(bot);

            for (var x : message.getStudentIds()) {
               // bot.sendText(x, "how are you");
                //bot.sendText(x, "hope your are doing well");
            }
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
