package org.graded_classes.graded_attendance.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.graded_classes.graded_attendance.GradedFxmlLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static org.graded_classes.graded_attendance.GradedResourceLoader.loadURL;

public class StudentAttendance implements Initializable {
    @FXML
    private TextField inputField;
    VBox box;
    ObservableList<HBox> observableList;
    FilteredList<HBox> filteredData;
    @FXML
    private VBox search_box;

    ListView<HBox> list;
    MainController homeController;
    GradedFxmlLoader gradedFxmlLoader;
    VBox outer_main_box;
    String id;
    public StudentAttendance(MainController homeController,
                             GradedFxmlLoader gradedFxmlLoader,
                             VBox outer_main_box, String id) {
        this.homeController = homeController;
        this.gradedFxmlLoader = gradedFxmlLoader;
        this.outer_main_box = outer_main_box;
        this.id = id;
    }

    @FXML
    void hide_search(MouseEvent event) {

        if (search_box.getChildren().size() == 2) {
            search_box.getChildren().removeLast();
        }
    }

    @FXML
    void show_search(MouseEvent event) {
        if (search_box.getChildren().size() == 1) {
            search_box.getChildren().add(box);
        }
    }

    @FXML
    void input(KeyEvent event) {
        String filter = inputField.getText();
        if (filter == null || filter.isEmpty()) {
            filteredData.setPredicate(s -> true);
        } else {
            filteredData.setPredicate(s -> {
                if (s.getId() != null)
                    return s.getId().contains(filter.toUpperCase());

                return true;
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            var x = new FXMLLoader(loadURL("fxml/list-for-search.fxml"));
            observableList = generate();
            filteredData = new FilteredList<>(observableList, s -> true);
            x.setControllerFactory(c -> new ListViewStudents(filteredData, inputField, gradedFxmlLoader, outer_main_box,id));
            box = x.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<HBox> generate() {
        var l = homeController.dataLoader.getStudentData();
        System.out.println(l);
        ArrayList<HBox> boxes = new ArrayList<>();
        for (var x : l.keySet()) {
            Label ed = new Label(x);
            ed.setMinWidth(50);
            Label name = new Label(l.get(x).name());
            HBox hBox = new HBox(ed, name);
            hBox.setSpacing(30);
            hBox.setId(x + " " + l.get(x).name());
            hBox.getStyleClass().add("hbox");
            boxes.add(hBox);
        }
        return FXCollections.observableList(boxes);
    }
}
