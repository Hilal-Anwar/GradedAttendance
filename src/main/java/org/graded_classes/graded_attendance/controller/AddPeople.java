package org.graded_classes.graded_attendance.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static org.graded_classes.graded_attendance.GradedResourceLoader.loadURL;

public class AddPeople {
   HomeController homeController;
   // @FXML
   // private WebView body_pane_back;
    //WebEngine webEngine1;

    public AddPeople(HomeController homeController) {

      this.homeController = homeController;
    }

    @FXML
    void initialize() {
        //webEngine1 = body_pane_back.getEngine();
        //webEngine1.load(loadURL("fxml/app.html").toString());
    }

    @FXML
    void onStudentClicked(MouseEvent event) {
        Parent dialog = null;
        try {
            var x = new FXMLLoader(loadURL("fxml/new_student_layout.fxml"));
            x.setControllerFactory(c -> new AddStudent(homeController));
            dialog = x.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        homeController.modalPane.show(dialog);
    }

    @FXML
    void onTeacherClicked(MouseEvent event) {
        Parent dialog = null;
        try {
            var x = new FXMLLoader(loadURL("fxml/new_teacher_layout.fxml"));
            //x.setControllerFactory(c -> new AddPeople(modalPane));
            dialog = x.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        homeController.modalPane.show(dialog);

    }

}
