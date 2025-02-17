module  org.graded_classes.graded_attendance {
    requires javafx.fxml;
    requires javafx.web;
    requires telegrambots;
    requires telegrambots.meta;
    requires java.sql;
    requires atlantafx.base;
    requires java.desktop;
    opens org.graded_classes.graded_attendance to javafx.fxml;
    exports org.graded_classes.graded_attendance;
    exports org.graded_classes.graded_attendance.data;
    opens org.graded_classes.graded_attendance.controller to javafx.fxml;
    exports org.graded_classes.graded_attendance.controller;
    opens org.graded_classes.graded_attendance.data to javafx.fxml;
    exports org.graded_classes.graded_attendance.components;
    opens org.graded_classes.graded_attendance.components to javafx.fxml;
}