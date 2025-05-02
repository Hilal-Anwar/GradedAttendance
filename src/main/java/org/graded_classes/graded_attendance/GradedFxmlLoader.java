package org.graded_classes.graded_attendance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.graded_classes.graded_attendance.controller.HomeController;
import org.graded_classes.graded_attendance.controller.StudentAttendance;

import java.io.IOException;

import static org.graded_classes.graded_attendance.GradedResourceLoader.loadURL;

public class GradedFxmlLoader {


    public Parent createView(R path) {
        Parent parent;
        try {
            parent = new FXMLLoader(loadURL(path.getPath())).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return parent;
    }

    public Parent createView(R path, Object controller) {
        Parent parent;
        try {
            var v = new FXMLLoader(loadURL(path.getPath()));
            v.setControllerFactory(c -> controller);
            parent = v.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return parent;
    }

}