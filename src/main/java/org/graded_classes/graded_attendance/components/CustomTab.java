package org.graded_classes.graded_attendance.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;

public class CustomTab extends Tab {
    private final String title;
    private final Parent root;
    private ScrollPane scrollPane;

    public CustomTab(String title, Parent root) {
        this.title = title;
        this.root = root;
        init();
    }


    private void init() {
        scrollPane = new ScrollPane();
        scrollPane.setContent(root);
        this.setContent(scrollPane);
        this.setClosable(false);
        this.setText(title);
    }
  

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

}
