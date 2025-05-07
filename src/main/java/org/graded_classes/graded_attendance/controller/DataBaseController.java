package org.graded_classes.graded_attendance.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.graded_classes.graded_attendance.GradedResourceLoader;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DataBaseController implements Initializable {
    MainController mainController;
    @FXML
    ListView<HBox> list;
    @FXML
    VBox database_tab;

    public DataBaseController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var map = mainController.dataLoader.getStudentData().values();
        ArrayList<HBox> studentInfo = new ArrayList<>();
       // studentInfo.add(getHeading());
        for (var x : map) {
            Label ed = new Label(x.ed_no());
            ed.setMinWidth(50);
            Label name = new Label(x.name());
            name.setMinWidth(200);
            name.setMaxWidth(200);
            Label grade = new Label(x._class());
            grade.setMinWidth(50);
            Label dob= new Label(x.dob());
            dob.setMinWidth(150);
            ImageView img = new ImageView(new Image(GradedResourceLoader.load("icons/delete.svg")));
            img.setOpacity(0);
            HBox tem=new HBox(img);
            HBox end_box = new HBox(tem);
            end_box.setAlignment(Pos.CENTER_RIGHT);
            end_box.setMinWidth(95);
            HBox.getHgrow(end_box);
            HBox hBox = new HBox(ed, name, grade,dob, end_box);

           tem.setOnMousePressed(u -> {
                System.out.println("Clicked");
                mainController.dataLoader.removeStudent(x);
                studentInfo.remove(hBox);
               list.setItems(FXCollections.observableList(studentInfo));
            });
            hBox.setOnMouseEntered(m -> {
                img.setOpacity(1);
            });
            hBox.setOnMouseExited( z-> {
                img.setOpacity(0);
            });
            hBox.setSpacing(30);
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setId(x.ed_no());
            hBox.getStyleClass().add("hbox");
            studentInfo.add(hBox);
        }
        list.setItems(FXCollections.observableList(studentInfo));
    }

    private HBox getHeading() {
        Label ed = new Label("ED No.");
        ed.setMinWidth(50);
        Label name = new Label("Name");
        name.setMinWidth(200);
        name.setMaxWidth(200);
        Label grade = new Label("Class");
        grade.setMinWidth(50);
        Label dob= new Label("Date of birth");
        dob.setMinWidth(150);
        HBox hBox = new HBox(ed, name, grade,dob);
        hBox.setSpacing(30);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setId("heading");
        hBox.getStyleClass().add("hbox1");
        return hBox;
    }
}
