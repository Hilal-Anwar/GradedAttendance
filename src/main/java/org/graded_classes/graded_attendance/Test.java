package org.graded_classes.graded_attendance;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.graded_classes.graded_attendance.components.FilterComboBox;

import java.util.ArrayList;
import java.util.Random;

public class Test extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        ArrayList<String> list = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            StringBuilder sb = new StringBuilder();
            int length = random.nextInt(10) + 5; // Random length between 5 and 15
            for (int j = 0; j < length; j++) {
                char c = (char) (random.nextInt(26) + 'a'); // Random lowercase letter
                sb.append(c);
            }
            list.add(sb.toString());
        }

        FilterComboBox filterComboBox=new FilterComboBox(FXCollections.observableArrayList(list));
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());

        Scene scene=new Scene(new VBox(filterComboBox),500,500);
        stage.setScene(scene);
        stage.show();
    }
}
