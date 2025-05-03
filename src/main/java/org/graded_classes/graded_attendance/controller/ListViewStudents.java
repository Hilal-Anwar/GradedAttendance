package org.graded_classes.graded_attendance.controller;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.graded_classes.graded_attendance.GradedFxmlLoader;
import org.graded_classes.graded_attendance.R;

import java.net.URL;
import java.util.ResourceBundle;

public class ListViewStudents implements Initializable {
    @FXML
    private ListView<HBox> list;
    FilteredList<HBox> hBoxes;
    TextField search;
    GradedFxmlLoader gradedFxmlLoader;
    VBox outer_main_box;
    String id;

    public ListViewStudents(FilteredList<HBox> hBoxes, TextField search, GradedFxmlLoader gradedFxmlLoader, VBox outer_main_box, String id) {
        this.hBoxes = hBoxes;
        this.search = search;
        this.gradedFxmlLoader = gradedFxmlLoader;
        this.outer_main_box = outer_main_box;
        this.id = id;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.setItems(hBoxes);
        list.setMinWidth(300);
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String id = newValue.getId();
                String name = id.substring(id.indexOf(" ") + 1);
                search.setText(name);
                createCustomDataView();
            }
        });

    }

    private void createCustomDataView() {
        System.out.println(id);
        if (id.equals("stu_atten")) {

        } else if (id.equals("st_fee")) {
            var feeReport = (VBox) gradedFxmlLoader.createView(R.student_fee_layout, new StudentFeeLayout());
            outer_main_box.getChildren().add(feeReport);
            VBox.setVgrow(feeReport, Priority.ALWAYS);
            System.out.println(outer_main_box.getChildren().size());
        }
    }
}
