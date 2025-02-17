package org.graded_classes.graded_attendance.controller;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ListViewStudents implements Initializable {
    @FXML
    private ListView<HBox> list;
    FilteredList<HBox> hBoxes;
    TextField search;

    public ListViewStudents(FilteredList<HBox> hBoxes, TextField search) {
        this.hBoxes = hBoxes;
        this.search = search;
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
            }
        });

    }
}
