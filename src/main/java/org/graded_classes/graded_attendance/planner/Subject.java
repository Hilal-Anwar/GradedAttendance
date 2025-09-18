package org.graded_classes.graded_attendance.planner;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import org.graded_classes.graded_attendance.R;

import java.net.URL;
import java.util.ResourceBundle;

public class Subject implements Initializable {
    Planner planner;
    public Subject(Planner planner) {
        this.planner = planner;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void createLessons(MouseEvent mouseEvent) {
        Label label = (Label) ((StackPane) mouseEvent.getSource()).lookup("#subject-name");
        planner.root.setValue(label.getText());
        planner.breadCrumb.setSelectedCrumb(planner.getTreeItemByIndex(planner.root,0));
        planner.div.setContent(planner.createView(R.add_lesson,new Lesson(planner)));
    }
}
