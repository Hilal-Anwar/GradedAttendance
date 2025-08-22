package org.graded_classes.graded_attendance.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.graded_classes.graded_attendance.data.GradedDataLoader;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.graded_classes.graded_attendance.GradedResourceLoader.loadURL;

public class StudentFeeLayout implements Initializable {
    @FXML
    private TextField amount_to_pay;

    @FXML
    private Label current_date;

    @FXML
    private Label day_and_time;

    @FXML
    private TextField due_amount;

    @FXML
    private TextField ed_no;

    @FXML
    private ComboBox<String> mode;

    @FXML
    private TextField name_of_receiver;
    @FXML
    Spinner<Integer> years;
    private Button selectedMonth;
    GradedDataLoader gradedDataLoader;
    String ed;

    public StudentFeeLayout(GradedDataLoader gradedDataLoader, String ed) {
        this.gradedDataLoader = gradedDataLoader;
        this.ed = ed;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Integer> listYears = FXCollections.observableArrayList(getList());
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(listYears);
        valueFactory.setValue(Calendar.getInstance().get(Calendar.YEAR));
        years.setValueFactory(valueFactory);
        System.out.println(gradedDataLoader.getStudentData());
        current_date.setText(LocalDate.now().getDayOfMonth() + " " +
                format(LocalDate.now().getMonth().toString()) +
                " " + LocalDate.now().getYear());
        mode.setItems(FXCollections.observableArrayList(List.of("Online", "Offline")));
        ed_no.setText(ed);
    }

    private ArrayList<Integer> getList() {
        return IntStream.rangeClosed(1990, 2100).
                boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    private String format(String date) {
        return date.charAt(0) + date.substring(1).toLowerCase();
    }

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void onMonthClicked(ActionEvent event) {
        if (selectedMonth != null) {
            selectedMonth.getStylesheets().set(0, loadURL("css/deSelectButton.css").toExternalForm());
        }
        selectedMonth = (Button) event.getSource();
        selectedMonth.getStylesheets().clear();
        selectedMonth.getStylesheets().add(loadURL("css/selectButton.css").toExternalForm());
    }

    @FXML
    void pay(ActionEvent event) {

    }
}
