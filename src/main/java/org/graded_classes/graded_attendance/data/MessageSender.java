package org.graded_classes.graded_attendance.data;

import javafx.scene.control.Alert;
import org.graded_classes.graded_attendance.controller.MainController;
import org.graded_classes.graded_attendance.messaging.TelegramBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class MessageSender {
    public MessageData message;
    TelegramBotsApi botsApi;
    TelegramBot bot;

    public MessageSender(DatabaseLoader databaseLoader, MainController mainController) {
        message = new MessageData(databaseLoader, mainController);
        try {
            botsApi = new TelegramBotsApi(DefaultBotSession.class);
            bot = new TelegramBot();
            bot.setMessageData(message);
            botsApi.registerBot(bot);

           // for (var x : message.getStudentIds()) {
                // bot.sendText(x, "how are you");
                //bot.sendText(x, "hope your are doing well");
            //}
        } catch (TelegramApiException e) {
            var alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeight(300);
            alert.setHeaderText("No internet connection");
            alert.setContentText("Please check your internet connection");
            alert.show();
        }
    }

    public void sendMessage(String message, long id) {
        bot.sendText(id, message);
    }
}
