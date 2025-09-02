package org.graded_classes.graded_attendance.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.graded_classes.graded_attendance.R;

import java.net.URL;
import java.util.ResourceBundle;

public class ListViewStudents implements Initializable {
    @FXML
    private ListView<HBox> list;
    StudentAttendance studentAttendance;
    String ed;
    AttendanceDataView attendanceDataView;
    String name;

    /* FilteredList<HBox> hBoxes;
     TextField search;
     GradedFxmlLoader gradedFxmlLoader;
     VBox outer_main_box;
     String id;
     MainController homeController;
     String ed;
 */
  /*  public ListViewStudents(FilteredList<HBox> hBoxes, TextField search, GradedFxmlLoader gradedFxmlLoader, VBox outer_main_box, String id, MainController homeController) {
        this.hBoxes = hBoxes;
        this.search = search;
        this.gradedFxmlLoader = gradedFxmlLoader;
        this.outer_main_box = outer_main_box;
        this.id = id;
        this.homeController = homeController;
    }
*/
    public ListViewStudents(StudentAttendance studentAttendance) {
        this.studentAttendance = studentAttendance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.setItems(studentAttendance.filteredData);
        list.setMinWidth(300);
        list.getSelectionModel().selectedItemProperty().
                addListener((observable,
                             oldValue, newValue) ->
                {
                    if (newValue != null) {
                        String id = newValue.getId();
                        ed = id.substring(0, id.indexOf(" "));
                        name = id.substring(id.indexOf(" ") + 1);
                        studentAttendance.inputField.setText(name);
                        createCustomDataView(ed);
                        studentAttendance.hide_search();
                    }
                });

    }

    private void createCustomDataView(String ed) {
        if (studentAttendance.id.equals("stu_atten")) {
            attendanceDataView = new AttendanceDataView(this.studentAttendance, ed);
            var attendance = studentAttendance.gradedFxmlLoader.createView(R.data_view, attendanceDataView);
            if (studentAttendance.outer_main_box.getChildren().size() > 3)
                studentAttendance.outer_main_box.getChildren().set(studentAttendance.outer_main_box.getChildren().size() - 1, attendance);
            else
                studentAttendance.outer_main_box.getChildren().add(attendance);
            VBox.setVgrow(attendance, Priority.ALWAYS);
        } else if (studentAttendance.id.equals("st_fee")) {
            var feeReport = studentAttendance.gradedFxmlLoader.createView(R.student_fee_layout,
                    new StudentFeeLayout(studentAttendance.mainController, ed, name));
            if (studentAttendance.outer_main_box.getChildren().size() > 3)
                studentAttendance.outer_main_box.getChildren().set(studentAttendance.outer_main_box.getChildren().size() - 1, feeReport);
            else
                studentAttendance.outer_main_box.getChildren().add(feeReport);

        }
    }
}
