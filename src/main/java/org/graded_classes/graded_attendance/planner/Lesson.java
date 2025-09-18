package org.graded_classes.graded_attendance.planner;

import atlantafx.base.controls.CustomTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.graded_classes.graded_attendance.R;

import java.net.URL;
import java.util.ResourceBundle;

public class Lesson implements Initializable {
    @FXML
    public VBox viewBox;
    public CustomTextField topic;
    Planner planner;
    public Lesson(Planner planner) {
        this.planner = planner;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void addSubtopic(ActionEvent actionEvent) {
      viewBox.getChildren().add(planner.createView(R.create_topic));
    }
}
