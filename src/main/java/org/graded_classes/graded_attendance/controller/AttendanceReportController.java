package org.graded_classes.graded_attendance.controller;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;

public class AttendanceReportController implements Initializable {

    String name;
    LinkedHashMap<String, String> view;
    String edNo;

    public AttendanceReportController(String name, String edNo, LinkedHashMap<String, String> view) {
        this.name = name;
        this.edNo = edNo;
        this.view = view;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize();
    }

    @FXML
    private Label nameLabel;
    @FXML
    private BarChart<String, Number> attendanceChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    @FXML
    public void initialize() {
        // Ensure x-axis shows our two categories
        xAxis.setLabel("Status");
        xAxis.setTickLabelsVisible(true);
        xAxis.setCategories(FXCollections.observableArrayList("Present", "Absent"));

        // Configure y-axis
        yAxis.setLabel("Days");
        yAxis.setForceZeroInRange(true);
        yAxis.setAutoRanging(true); // Will be set to false when you pass data

        // Hide legend if you only show one series
        attendanceChart.setLegendVisible(false);
        nameLabel.setText(name);
        int missingDay = Integer.parseInt(view.get("Missing Dates").trim());
        int present = Integer.parseInt(view.get(edNo).trim());
        System.out.println("Missing Dates: " + missingDay);
        System.out.println("Present Dates: " + present);

        List<Boolean> daily = java.util.Arrays.asList(
                true, true, false, true, true, true, true,  // week 1
                true, false, true, true, true, true, false, // week 2
                true, true, true, false, true, true, true,  // week 3
                true, true, false, true, true, true, false  // week 4
        );

        setAttendanceData(present,missingDay);
    }

    /**
     * Set the student name displayed above the chart.
     */
    public void setStudentName(String name) {
        nameLabel.setText(name != null ? name : "Name");
    }

    /**
     * Populate chart with present/absent totals.
     */
    public void setAttendanceData(int presentDays, int absentDays) {
        int total = Math.max(0, presentDays) + Math.max(0, absentDays);

        // Configure y-axis bounds based on total days (or month length)
        if (total > 0) {
            yAxis.setAutoRanging(false);
            yAxis.setLowerBound(0);

            // Option A: bound by total days passed in
            int upper = Math.max(total, 5);

            // Option B (monthly): uncomment to bound by current month length
            upper = Math.max(LocalDate.now().lengthOfMonth(), upper);

            yAxis.setUpperBound(upper);
            yAxis.setTickUnit(Math.max(1, Math.ceil(upper / 5.0)));
        } else {
            yAxis.setAutoRanging(true);
        }

        // Build series
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Attendance");
        XYChart.Data<String, Number> present = new XYChart.Data<>("Present", presentDays);
        series.getData().add(present);
        series.getData().add(new XYChart.Data<>("Absent", absentDays));

        // Apply to chart
        attendanceChart.getData().setAll(series);
        attendanceChart.setCategoryGap(50);
        // Color the bars (green for present, red for absent)
        Platform.runLater(() -> {
            for (XYChart.Data<String, Number> d : series.getData()) {
                String category = d.getXValue();
                String color = "Present".equals(category) ? "#2ecc71" : "#e74c3c"; // green / red
                if (d.getNode() != null) {
                    d.getNode().setStyle("-fx-bar-fill: " + color + ";");
                }
            }
        });
    }

    /**
     * Convenience: if you store daily attendance as booleans (true=present, false=absent).
     */
    public void setAttendanceFromBooleans(List<Boolean> dailyPresence) {
        if (dailyPresence == null || dailyPresence.isEmpty()) {
            setAttendanceData(0, 0);
            return;
        }
        int present = (int) dailyPresence.stream().filter(Boolean::booleanValue).count();
        setAttendanceData(present, dailyPresence.size() - present);
    }
}
