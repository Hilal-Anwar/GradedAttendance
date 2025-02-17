package org.graded_classes.graded_attendance.components;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomDataView implements Initializable {
    @FXML
    TabPane tabPane;
    CustomTab[] tabs;
    @FXML
    HBox footer;

    public CustomDataView(HBox footer, CustomTab... tabs) {
        this.footer = footer;
        this.tabs = tabs;
    }

    public CustomDataView(CustomTab[] tabs) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
