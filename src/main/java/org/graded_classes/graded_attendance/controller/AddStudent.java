package org.graded_classes.graded_attendance.controller;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.graded_classes.graded_attendance.data.Student;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

public class AddStudent implements Initializable {

    @FXML
    private ComboBox<String> _class, blood_group, gender;

    @FXML
    private CheckBox agree;

    @FXML
    private TextField adahar_no, ed_no, email_id,
            father_f_name, father_l_name, father_occ, first_name,
            g_num, info_about, last_name, mother_f_name, mother_l_name,
            mother_occ, previous_ins_name, reason_leaving, school_n, suggestions, telegram_id;

    @FXML
    private TextArea address;

    @FXML
    private DatePicker dob;
    private final String[] blood_groups_list = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
    private final String[] gender_list = {"Male", "Female"};
    private final String[] classes_list = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII"};
    private final HashSet<String> list_of_subjects = new HashSet<>();
    HomeController homeController;

    public AddStudent(HomeController homeController) {
        this.homeController = homeController;
        System.out.println(homeController.dataLoader.getStudentData());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender.setItems(FXCollections.observableArrayList(gender_list));
        blood_group.setItems(FXCollections.observableArrayList(blood_groups_list));
        _class.setItems(FXCollections.observableArrayList(classes_list));
    }

    @FXML
    void apply(ActionEvent event) {

        Button button = (Button) event.getSource();
        if (button.getText().equals("Apply")) {
            var studentInfo = getStudent();
            if (agree.isSelected()){
                System.out.println(studentInfo);
                homeController.dataLoader.addStudent(studentInfo);
                homeController.modalPane.hide();
            }
        } else if (button.getText().equals("Cancel")) {

        }
    }

    public Student getStudent() {
        String info = info_about.getText();
        return new Student(
                getNextED(),
                first_name.getText() + " " + last_name.getText(),
                email_id.getText(),
                blood_group.getSelectionModel().getSelectedItem(),
                g_num.getText(),
                adahar_no.getText(),
                father_f_name.getText() + " " + father_l_name.getText(),
                mother_f_name.getText() + " " + mother_l_name.getText(),
                _class.getSelectionModel().getSelectedItem(),
                gender.getSelectionModel().getSelectedItem(),
                dob.getValue().toString(),
                address.getText(),
                father_occ.getText(),
                mother_occ.getText(),
                previous_ins_name.getText(),
                reason_leaving.getText(),
                school_n.getText(),
                suggestions.getText(),
                list_of_subjects.toArray(new String[0]),
                telegram_id.getText()
        );
    }

    @FXML
    private void onSubjectSelection(ActionEvent event) {
        CheckBox check = (CheckBox) event.getSource();
        String name = check.getText();
        if (check.isSelected())
            list_of_subjects.add(name);
        else
            list_of_subjects.remove(name);

    }
    public String getNextED() {
       if (homeController.dataLoader.getStudentData().isEmpty())
            return "ED01";
        else {
            var endKey = homeController.dataLoader.getStudentData().lastEntry().getKey();

            return generate(endKey);
        }
    }

    public String generate(String endKey) {
        long l = Long.parseLong(endKey.replaceAll("ED", ""))+1;
        return l < 10 ? "ED0" + l : "ED" + l;
    }
    public void init() {

    }
}

