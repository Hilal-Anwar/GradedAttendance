package org.graded_classes.graded_attendance.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.graded_classes.graded_attendance.GradedFxmlLoader;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

import static org.graded_classes.graded_attendance.GradedResourceLoader.loadURL;

public class StudentAttendance implements Initializable {
    @FXML
    TextField inputField;
    VBox box;
    ObservableList<HBox> observableList;
    FilteredList<HBox> filteredData;
    @FXML
    private VBox search_box;
    @FXML
    Button checkIn_out;
    ListView<HBox> list;
    MainController mainController;
    GradedFxmlLoader gradedFxmlLoader;
    VBox outer_main_box;
    String id;
    ListViewStudents listViewStudents;
    LinkedHashMap<String, String[]> attendanceMap = new LinkedHashMap<>();

    public StudentAttendance(MainController mainController,
                             GradedFxmlLoader gradedFxmlLoader,
                             VBox outer_main_box, String id) {
        this.mainController = mainController;
        this.gradedFxmlLoader = gradedFxmlLoader;
        this.outer_main_box = outer_main_box;
        this.id = id;
        init();
    }

    private void init() {
        // Format today's date as a valid column name
        String columnName = LocalDate.now().toString();
        String tableName = "atte_" + LocalDate.now().getMonth().getDisplayName(TextStyle.SHORT, Locale.UK);
        try (Statement stmt = mainController.gradedDataLoader.databaseLoader.getStatement()) {

            // Get existing columns
            ResultSet rs = stmt.executeQuery("PRAGMA table_info(" + tableName + ")");
            boolean columnExists = false;

            while (rs.next()) {
                String existingColumn = rs.getString("name");
                if (existingColumn.equalsIgnoreCase(columnName)) {
                    columnExists = true;
                    break;
                }
            }

            // Add column if it doesn't exist
            if (!columnExists) {
                String alterSQL = "ALTER TABLE \"" + tableName + "\" ADD COLUMN \"" + columnName + "\" TEXT";
                stmt.executeUpdate(alterSQL);
                System.out.println("Column '" + columnName + "' added to table '" + tableName + "'.");
            } else {
                System.out.println("Column '" + columnName + "' already exists in table '" + tableName + "'.");
            }
            ResultSet r = stmt.executeQuery("select * from " + tableName);
            while (r.next()) {

                attendanceMap.put(r.getString("ed_no"), getFromArray(r.getString(columnName)));
            }
            System.out.println(attendanceMap);

        } catch (SQLException _) {

        }
    }

    @FXML
    void hide_search() {

        if (search_box.getChildren().size() == 2) {
            search_box.getChildren().removeLast();
        }
    }

    @FXML
    void show_search() {
        if (search_box.getChildren().size() == 1) {
            search_box.getChildren().add(box);
        }
    }

    @FXML
    void input(KeyEvent event) {
        String filter = inputField.getText();
        if (filter == null || filter.isEmpty()) {
            filteredData.setPredicate(s -> true);
        } else {
            filteredData.setPredicate(s -> {
                if (s.getId() != null)
                    return s.getId().contains(filter.toUpperCase());

                return true;
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            var x = new FXMLLoader(loadURL("fxml/list-for-search.fxml"));
            observableList = generate();
            filteredData = new FilteredList<>(observableList, s -> true);
            listViewStudents = new ListViewStudents(this);
            x.setControllerFactory(c -> listViewStudents);
            box = x.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<HBox> generate() {
        var l = mainController.gradedDataLoader.getStudentData();
        ArrayList<HBox> boxes = new ArrayList<>();
        for (var x : l.keySet()) {
            Label ed = new Label(x);
            ed.setMinWidth(50);
            Label name = new Label(l.get(x).name());
            HBox hBox = new HBox(ed, name);
            hBox.setSpacing(30);
            hBox.setId(x + " " + l.get(x).name());
            hBox.getStyleClass().add("hbox");
            boxes.add(hBox);
        }
        return FXCollections.observableList(boxes);
    }

    @FXML
    public void doAction(ActionEvent event) {
        Button source = (Button) event.getSource();
        String columnName = LocalDate.now().toString();
        String tableName = "atte_" + LocalDate.now().getMonth().getDisplayName(TextStyle.SHORT, Locale.UK);
        updateAttendance(tableName, columnName, source);
    }

    private String[] getFromArray(String string) {
        if (string == null) {
            return new String[3];
        }
        string = string.replace("[", "").replace("]", "");
        return string.split(",");

    }

    public void updateHomeWorkStatus(String val) {
        Connection conn = mainController.gradedDataLoader.databaseLoader.getConnection();
        String edNo = listViewStudents.ed;
        String columnName = LocalDate.now().toString();
        String tableName = "atte_" + LocalDate.now().getMonth().getDisplayName(TextStyle.SHORT, Locale.UK);
        try (PreparedStatement checkStmt = conn.prepareStatement("SELECT 1 FROM " + tableName + " WHERE ed_no = ?")) {
            checkStmt.setString(1, edNo);
            ResultSet checkRs = checkStmt.executeQuery();

            if (checkRs.next()) {
                String updateSQL = "UPDATE \"" + tableName + "\" SET \"" + columnName + "\" = ? WHERE ed_no = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSQL);
                String[] va = attendanceMap.get(edNo);
                va[3] = val;
                updateStmt.setString(1, Arrays.toString(va));
                updateStmt.setString(2, edNo);
                updateStmt.executeUpdate();
                System.out.println("Updated column '" + columnName + "' for ed_no '" + edNo + "'.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateAttendance(String tableName, String columnName, Button source) {
        String timeStamp = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
        Connection conn = mainController.gradedDataLoader.databaseLoader.getConnection();
        String edNo = listViewStudents.ed;
        try (PreparedStatement checkStmt = conn.prepareStatement("SELECT 1 FROM " + tableName + " WHERE ed_no = ?")) {
            checkStmt.setString(1, edNo);
            ResultSet checkRs = checkStmt.executeQuery();

            if (checkRs.next()) {
                // Update the column for the student
                String updateSQL = "UPDATE \"" + tableName + "\" SET \"" + columnName + "\" = ? WHERE ed_no = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSQL);
                String[] va = attendanceMap.get(edNo);
                System.out.println(Arrays.toString(va));
                if (source.getText().equals("Check In")) {
                    va[0] = timeStamp;
                    System.out.println(Arrays.toString(va));
                    updateStmt.setString(1, Arrays.toString(va));
                    attendanceMap.replace(edNo, va);
                    listViewStudents.attendanceDataView.update();
                    source.setText("Check Out");
                } else if (source.getText().equals("Check Out")) {
                    va[1] = timeStamp;
                    updateStmt.setString(1, Arrays.toString(va));
                    attendanceMap.replace(edNo, va);
                    source.setVisible(false);
                    listViewStudents.attendanceDataView.update();
                }

                updateStmt.setString(2, edNo);
                updateStmt.executeUpdate();
                System.out.println("Updated column '" + columnName + "' for ed_no '" + edNo + "'.");
            } else {
                System.out.println("Student with ed_no '" + edNo + "' not found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
