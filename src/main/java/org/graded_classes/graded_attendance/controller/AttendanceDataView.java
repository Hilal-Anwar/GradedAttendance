package org.graded_classes.graded_attendance.controller;

import atlantafx.base.controls.ToggleSwitch;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.graded_classes.graded_attendance.data.Student;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

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
    private ToggleSwitch homeworkSwitch;

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
        if (varify(studentAttendance.attendanceMap.get(ed_no)))
            studentAttendance.checkIn_out.setVisible(true);
        else {
            update();
        }
    }

    private boolean varify(String[] s) {
        return s[0] == null || s[1] == null;
    }

    public static String firstLetterToUpperCase(String s) {
        String firstWord = s.substring(0, s.indexOf(' ')).trim();
        String secondWord = s.substring(s.indexOf(' ')).trim();
        return firstWord.substring(0, 1).toUpperCase() + firstWord.substring(1).toLowerCase() + " "
                + secondWord.substring(0, 1).toUpperCase() + secondWord.substring(1).toLowerCase();
    }

    public void update() {
        var x = studentAttendance.attendanceMap.get(ed_no);
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
        if (x[1] != null && x[2] != null) {
            status.setText("Present");
            status.setStyle("-fx-text-fill: #1C75BC;");
        }

    }

    @FXML
    void whenClicked(MouseEvent event) {
            ToggleSwitch toggleSwitch= (ToggleSwitch) event.getSource();
            if (toggleSwitch.isSelected()){
                toggleSwitch.setText("Submitted");
                studentAttendance.attendanceMap.get(ed_no)[3]="Submitted";
            }
            else {
                toggleSwitch.setText("Not Submitted");
                studentAttendance.attendanceMap.get(ed_no)[3]="Not Submitted";

            }
    }
}
