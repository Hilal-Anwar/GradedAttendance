package org.graded_classes.graded_attendance.controller;

import atlantafx.base.controls.ToggleSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import org.graded_classes.graded_attendance.GradedResourceLoader;
import org.graded_classes.graded_attendance.data.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.graded_classes.graded_attendance.GradedResourceLoader.loadURL;

public class AttendanceDataView implements Initializable {
    @FXML
    private Label c_in_status;

    @FXML
    private Label c_out_status;

    @FXML
    private Label checkInTime;

    @FXML
    private Label checkOutTime;

    @FXML
    private Label dueAmount;
    @FXML
    private Tab edit, info;
    @FXML
    public ToggleSwitch homeworkSwitch;
    @FXML
    TabPane tabPane;
    @FXML
    private Label lastPayment;

    @FXML
    private Label status;

    @FXML
    private Label uClass;

    @FXML
    private Label uId;
    @FXML
    private Label uName;
    StudentAttendance studentAttendance;
    String ed_no;
    EditStudentData editStudentData;

    public AttendanceDataView(StudentAttendance studentAttendance, String ed_no) {
        this.studentAttendance = studentAttendance;
        this.ed_no = ed_no;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var x = studentAttendance.mainController.gradedDataLoader;
        Student student = x.getStudentData().get(ed_no);
        uId.setText(student.ed_no());
        uName.setText(firstLetterToUpperCase(student.name()));
        uClass.setText(student._class());
        if (varify(studentAttendance.attendanceMap.get(ed_no))) {
            studentAttendance.checkIn_out.setVisible(true);
            if (studentAttendance.attendanceMap.get(ed_no)[0] == null)
                studentAttendance.checkIn_out.setText("Check In");
            else if (studentAttendance.attendanceMap.get(ed_no)[1] == null)
                studentAttendance.checkIn_out.setText("Check Out");
        } else {
            studentAttendance.checkIn_out.setVisible(false);
        }
        update();
        edit.setOnSelectionChanged(_ -> {
            extracted();
        });
    }

    private void extracted() {
        var y = new FXMLLoader(loadURL("fxml/editData.fxml"));
        editStudentData = new EditStudentData(studentAttendance.mainController.gradedDataLoader.getStudentData(), ed_no);
        y.setControllerFactory(c -> editStudentData);
        try {
            edit.setContent(y.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean varify(String[] s) {
        return s == null || (s[0] == null || s[1] == null);
    }

    public static String firstLetterToUpperCase(String s) {
        String firstWord = s.substring(0, s.indexOf(' ')).trim();
        String secondWord = s.substring(s.indexOf(' ')).trim();
        return firstWord.substring(0, 1).toUpperCase() + firstWord.substring(1).toLowerCase() + " "
                + secondWord.substring(0, 1).toUpperCase() + secondWord.substring(1).toLowerCase();
    }

    public void update() {
        var x = studentAttendance.attendanceMap.get(ed_no);
        if (x != null) {
            if (x[0] != null) {
                checkInTime.setText(x[0]);
                c_in_status.setText("Message sent");
                checkInTime.setStyle("-fx-text-fill: #1C75BC;");
                c_in_status.setStyle("-fx-text-fill: #1C75BC;");
            }
            if (x[1] != null) {
                checkOutTime.setText(x[1]);
                c_out_status.setText("Message sent");
                checkOutTime.setStyle("-fx-text-fill: #1C75BC;");
                c_out_status.setStyle("-fx-text-fill: #1C75BC;");

            }
            if (x[0] != null && x[1] != null) {
                status.setText("Present");
                status.setStyle("-fx-text-fill: #1C75BC;");
            }
            if (x[2] != null) {
                homeworkSwitch.setSelected(true);
                homeworkSwitch.setText("Submitted");
            }
        }

    }

    @FXML
    void whenClicked(MouseEvent event) {
        if (homeworkSwitch.isSelected()) {
            homeworkSwitch.setText("Submitted");
            studentAttendance.attendanceMap.get(ed_no)[2] = "Submitted";
        } else {
            homeworkSwitch.setText("Not Submitted");
            studentAttendance.attendanceMap.get(ed_no)[2] = "Not Submitted";

        }
    }

    @FXML
    void onCancel() {
        studentAttendance.outer_main_box.getChildren().removeLast();
        studentAttendance.inputField.setText("");
        studentAttendance.searchCrossIcon.setImage(new Image(GradedResourceLoader.load("icons/search.svg")));

    }

    @FXML
    void onOK() {
        if (info.isSelected()) {
            if (!edit.isSelected()) {
                if (homeworkSwitch.isSelected())
                    studentAttendance.updateHomeWorkStatus("Submitted");
                else
                    studentAttendance.updateHomeWorkStatus("Not Submitted");
            } else {
                editStudentData.update(studentAttendance.mainController.gradedDataLoader.databaseLoader.getConnection());
            }
        } else if (edit.isSelected()) {
            editStudentData.update(studentAttendance.mainController.gradedDataLoader.databaseLoader.getConnection());
        }
    }

    @FXML
    void viewReport(ActionEvent event) {
    }


}
