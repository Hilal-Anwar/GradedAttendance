package org.graded_classes.graded_attendance.data;

import atlantafx.base.theme.Styles;
import javafx.application.Platform;
import org.graded_classes.graded_attendance.controller.MainController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class MessageData {
    Statement statement;
    HashSet<Long> studentIds = new HashSet<>();
    DatabaseLoader databaseLoader;
    MainController mainController;

    public HashSet<Long> getStudentIds() {
        return studentIds;
    }

    public MessageData(DatabaseLoader databaseLoader, MainController mainController) {
        this.mainController = mainController;
        this.databaseLoader = databaseLoader;
        statement = databaseLoader.getStatement();
        try (ResultSet rs = statement.executeQuery("select * from STUDENT_CONTACT")) {
            while (rs.next()) {
                var v = rs.getString("telegram_id");
                if (v != null && !v.isEmpty())
                    studentIds.add(Long.parseLong(v));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateTelegramId(String edNo, String name, String _class, String  newTelegramId) {
        String updateSQL = """
                UPDATE STUDENT_CONTACT SET telegram_id = ? WHERE ed_no = ? AND TRIM(LOWER(name)) = TRIM(LOWER(?)) AND TRIM(LOWER(class)) = TRIM(LOWER(?))
                """;
        try (PreparedStatement pst = databaseLoader.getConnection().prepareStatement(updateSQL)) {
            pst.setString(1, newTelegramId);
            pst.setString(2, edNo);
            pst.setString(3, name);
            pst.setString(4, _class);
            int rowsAffected = pst.executeUpdate();
            //System.out.println(Thread.currentThread().getName() + ": Telegram ID: " + newTelegramId);
            if (rowsAffected > 0) {
                Platform.runLater(() -> {
                    //System.out.println(Thread.currentThread().getName() + ": Telegram ID: " + newTelegramId);
                    mainController.sendNotification(name + " was added to graded messaging system", Styles.SUCCESS);
                   // studentIds.add(Long.parseLong(newTelegramId));
                });
                studentIds.add(Long.parseLong(newTelegramId));
                return true;
            } else {
                return false;
            }

        } catch (SQLException _) {

        }
        return false;
    }
}
