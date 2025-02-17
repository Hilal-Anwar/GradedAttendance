package org.graded_classes.graded_attendance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static org.graded_classes.graded_attendance.GradedResourceLoader.loadURL;

public class GradedFxmlLoader {
    public static VBox add_people, add_student, custom_data_view, fee_report, main_layout,
            new_student_layout, new_teacher_layout, student_attendance_layout,
            student_fee_layout, teacher_attendance_layout, teacher_payment_layout;

    static {
        //main_layout=createView("fxml/main_layout.fxml");
        student_attendance_layout = createView("fxml/student_attendance_layout.fxml");
    }

    private static VBox createView(String path) {
        VBox parent = null;
        try {
            parent = new FXMLLoader(loadURL(path)).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return parent;
    }

}
