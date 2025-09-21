package org.graded_classes.graded_attendance.planner;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SubtopicCreator implements Initializable {
    private final Planner planner;
    private final String name;
    public Label subtopicName;
    @FXML
    private Button editSubtopic;

    @FXML
    private Button removeSubtopic;

    public SubtopicCreator(Planner planner, String name) {
        this.planner = planner;
        this.name = name;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         subtopicName.setText(name);
    }
}
