package org.graded_classes.graded_attendance.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StudentFeeLayout implements Initializable {
    @FXML
    private TextField amount_to_pay;

    @FXML
    private Text current_date;

    @FXML
    private Text day_and_time;

    @FXML
    private TextField due_amount;

    @FXML
    private TextField ed_no;

    @FXML
    private ComboBox<?> mode;

    @FXML
    private TextField name_of_receiver;
    @FXML
    Spinner<Integer> years;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Integer> listYears = FXCollections.observableArrayList(getList());
        // Value factory.
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.ListSpinnerValueFactory<>(listYears);

        // Default value
        valueFactory.setValue(Calendar.getInstance().get(Calendar.YEAR));

        years.setValueFactory(valueFactory);
    }

    private ArrayList<Integer> getList() {
        return IntStream.rangeClosed(1990, 2100).
                boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void onMonthClicked(ActionEvent event) {

    }

    @FXML
    void pay(ActionEvent event) {

    }
}
