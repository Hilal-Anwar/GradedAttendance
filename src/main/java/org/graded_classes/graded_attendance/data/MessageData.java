package org.graded_classes.graded_attendance.data;

import atlantafx.base.theme.Styles;
import javafx.application.Platform;
import org.graded_classes.graded_attendance.controller.MainController;

import java.sql.Statement;
import java.util.Objects;
import java.util.stream.Stream;

public class MessageData {
    Statement statement;
    DatabaseLoader databaseLoader;
    MainController mainController;

    public Stream<String> getStudentId(){
        return mainController.gradedDataLoader.studentData.values().stream().
                map(Student::telegram_id).filter(Objects::nonNull);
    }
    public MessageData(DatabaseLoader databaseLoader, MainController mainController) {
        this.mainController = mainController;
        this.databaseLoader = databaseLoader;
        statement = databaseLoader.getStatement();
    }

    public boolean updateTelegramId(String edNo, String name, String _class, String newTelegramId) {
        var rowsAffected = mainController.gradedDataLoader.getStudentData().get(edNo).updateTelegram(databaseLoader.getConnection(), edNo, name, _class, newTelegramId);
        if (rowsAffected > 0) {
            Platform.runLater(() -> mainController.sendNotification(name + " was added to graded messaging system", Styles.SUCCESS));
            mainController.gradedDataLoader.getStudentData().get(edNo).setTelegram_id(newTelegramId);
            return true;
        } else {
            return false;
        }
    }
}
