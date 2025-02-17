package org.graded_classes.graded_attendance.controller;

import atlantafx.base.controls.ModalPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.graded_classes.graded_attendance.GradedFxmlLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.graded_classes.graded_attendance.GradedResourceLoader.loadStream;
import static org.graded_classes.graded_attendance.GradedResourceLoader.loadURL;

public class MainController implements Initializable {
    @FXML
    AnchorPane searchBarHolder;
    @FXML
    VBox outer_main_box;
    @FXML
    VBox stu_atten, tea_atten, st_fee, tea_fee, tea_prog;
    @FXML
    WebView background;
    @FXML
    WebView my_logo;
    WebEngine webEngine1, webEngine2;
    Stage stage;
    @FXML
    ModalPane modalPane;

    public MainController(Stage stage) {
        this.stage = stage;
    }

    private VBox selectedItem;

    @FXML
    void onItemClick(MouseEvent mouseEvent) {
        VBox vBox = (VBox) mouseEvent.getSource();
        System.out.println(vBox);
        if (vBox.getId().equals(stu_atten.getId())) {
            toggleOut(getIconPaths());
            toggleIn(vBox, "icons/attendance_student.png");
            selectedItem = stu_atten;
        } else if (vBox.getId().equals(tea_atten.getId())) {
            toggleOut(getIconPaths());
            toggleIn(vBox, "icons/teacher_attendance.png");
            selectedItem = tea_atten;

        } else if (vBox.getId().equals(st_fee.getId())) {
            toggleOut(getIconPaths());
            toggleIn(vBox, "icons/fee_book.png");
            selectedItem = st_fee;

        } else if (vBox.getId().equals(tea_fee.getId())) {
            toggleOut(getIconPaths());
            toggleIn(vBox, "icons/teacher_payment.png");
            selectedItem = tea_fee;

        } else if (vBox.getId().equals(tea_prog.getId())) {
            toggleOut(getIconPaths());
            toggleIn(vBox, "icons/teaching_progress.png");
            selectedItem = tea_prog;
        }
        VBox new_box;
        try {
            new_box = new FXMLLoader(loadURL(getView(vBox))).load();
            System.out.println(new_box);
            VBox.setVgrow(new_box, Priority.ALWAYS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AnchorPane.setRightAnchor(new_box, 0.0);
        AnchorPane.setLeftAnchor(new_box, 0.0);
        searchBarHolder.getChildren().set(searchBarHolder.getChildren().size()-1,new_box);
        //outer_main_box.getChildren().set(outer_main_box.getChildren().size() - 1, new_box);
    }


    private String getView(VBox vBox) {
        if (vBox.getId().equals(stu_atten.getId())||vBox.getId().equals(st_fee.getId()))
            return "fxml/student_attendance_layout.fxml";
        else if (vBox.getId().equals(tea_atten.getId())||vBox.getId().equals(tea_fee.getId()))
            return "fxml/teacher_attendance_layout.fxml";
        /*else if (vBox.getId().equals(st_fee.getId()))
            return "fxml/student_fee_layout.fxml";
        else if (vBox.getId().equals(tea_fee.getId()))
            return "fxml/teachers_payment_layout.fxml";*/
        else if (vBox.getId().equals(tea_prog.getId()))
            return "fxml/teaching_progress_layout.fxml";
        return "";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(modalPane);
        webEngine1 = background.getEngine();
        webEngine2 = my_logo.getEngine();
        webEngine2.load(loadURL("fxml/app_logo.html").toString());
        webEngine1.load(loadURL("fxml/app.html").toString());
        selectedItem = stu_atten;
        try {
            //FXMLLoader loader = new FXMLLoader(loadURL("fxml/student_attendance_layout.fxml"));
            VBox studentAttendanceLayout = /*loader.load();*/GradedFxmlLoader.student_attendance_layout;
            VBox dummy_box = new VBox();
            dummy_box.setMinHeight(70);
            var lay = new FXMLLoader(loadURL("fxml/data-view.fxml"));
           // var x = new FXMLLoader(loadURL("fxml/new_student_layout.fxml"));
           // lay.setControllerFactory(c->new DataView(x));
            VBox ABB=lay.load();
            VBox.setVgrow(studentAttendanceLayout, Priority.ALWAYS);
            AnchorPane.setRightAnchor(studentAttendanceLayout, 0.0);
            AnchorPane.setLeftAnchor(studentAttendanceLayout, 0.0);
            VBox.setVgrow(ABB, Priority.ALWAYS);
            searchBarHolder.getChildren().add(studentAttendanceLayout);
            outer_main_box.getChildren().add(dummy_box);
            outer_main_box.getChildren().add(ABB);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private void toggleIn(VBox vBox, String paths) {
        ImageView imageView = (ImageView) vBox.lookup("#box_icon");
        imageView.setImage(new Image(loadStream(paths)));
        vBox.getStylesheets().removeFirst();
        vBox.getStylesheets().add(loadURL("css/toggle_in.css").toExternalForm());
    }

    private void toggleOut(String paths) {
        ImageView imageView = (ImageView) selectedItem.lookup("#box_icon");
        imageView.setImage(new Image(loadStream(paths)));
        selectedItem.getStylesheets().removeFirst();
        selectedItem.getStylesheets().add(loadURL("css/toggle_out.css").toExternalForm());
    }

    private String getIconPaths() {
        if (selectedItem.equals(stu_atten)) {
            return "icons/attendance_student_normal.png";
        } else if (selectedItem.equals(tea_atten)) {
            return "icons/teacher_attendance_normal.png";
        } else if (selectedItem.equals(st_fee)) {
            return "icons/fee_book_normal.png";
        } else if (selectedItem.equals(tea_fee)) {
            return "icons/teacher_payment_normal.png";
        } else if (selectedItem.equals(tea_prog)) {
            return "icons/teaching_progress_normal.png";
        }
        return "icons/fee_book_normal.png";
    }

    @FXML
    public void onButtonPressed(ActionEvent actionEvent) {
        AnchorPane dialog = null;
        try {
            dialog = FXMLLoader.load(loadURL("fxml/time_table_classes.fxml"));
            dialog.setStyle("-fx-background-color: #fafafa;");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        modalPane.show(dialog);

    }
    @FXML
    public void onButtonPressedNewStudent(ActionEvent actionEvent) {
        Parent dialog = null;
        try {
            var x = new FXMLLoader(loadURL("fxml/add_people.fxml"));
            x.setControllerFactory(c->new AddPeople(modalPane));
            dialog=x.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        modalPane.show(dialog);
    }

}