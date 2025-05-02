package org.graded_classes.graded_attendance.controller;

import atlantafx.base.controls.ModalPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.graded_classes.graded_attendance.GradedFxmlLoader;
import org.graded_classes.graded_attendance.GradedResourceLoader;
import org.graded_classes.graded_attendance.R;
import org.graded_classes.graded_attendance.data.DataLoader;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    HBox selectedTab;
    Stage stage;
    Node home, chat, calendar;
    Map<String, Image> toggleInImages = Map.of(
            "home", new Image(GradedResourceLoader.load("icons/home_in.svg")),
            "chat", new Image(GradedResourceLoader.load("icons/chat_in.svg")),
            "calender", new Image(GradedResourceLoader.load("icons/calendar_month_in.svg")),
            "database", new Image(GradedResourceLoader.load("icons/database_in.svg")));
    Map<String, Image> toggleOutImages = Map.of(
            "home", new Image(GradedResourceLoader.load("icons/home.svg")),
            "chat", new Image(GradedResourceLoader.load("icons/chat.svg")),
            "calender", new Image(GradedResourceLoader.load("icons/calendar_month.svg")),
            "database", new Image(GradedResourceLoader.load("icons/database.svg")));
    @FXML
    ModalPane modalPane;
    GradedFxmlLoader gradedFxmlLoader = new GradedFxmlLoader();
    @FXML
    BorderPane main_view;
    public DataLoader dataLoader = new DataLoader();

    public MainController(Stage stage) {
        this.stage = stage;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        home = gradedFxmlLoader.createView(R.home_layout, new HomeController(modalPane,
                dataLoader, this));
        chat = gradedFxmlLoader.createView(R.chat_layout, new ChatController());
        calendar = gradedFxmlLoader.createView(R.calendar_layout, new CalenderController());
        //database = gradedFxmlLoader.createView(R.database_layout, new DataBaseController(this));
        main_view.setCenter(navigateView("home"));
        System.out.println(dataLoader.getStudentData());
    }

    @FXML
    void navigation(MouseEvent event) {
        HBox root = (HBox) event.getSource();
        ImageView imageView = ((ImageView) root.getChildren().getLast());
        Rectangle rectangle = (Rectangle) root.getChildren().getFirst();
        toggleOut(selectedTab, (Rectangle) selectedTab.getChildren().getFirst(),
                (ImageView) selectedTab.getChildren().getLast());
        toggleIn(root, rectangle, imageView);
        main_view.setCenter(navigateView(root.getId()));
        selectedTab = root;
    }

    private Node navigateView(String id) {
        return switch (id) {
            case "home" -> home;
            case "chat" -> chat;
            case "calender" -> calendar;
            case "database" -> gradedFxmlLoader.createView(R.database_layout, new DataBaseController(this));
            default -> null;
        };
    }

    private void toggleIn(HBox root, Rectangle rectangle, ImageView imageView) {
        root.setStyle("-fx-background-color:  #1C75BC;-fx-background-radius: 0 5 5 0");
        rectangle.setFill(Paint.valueOf("#fafafa"));
        imageView.setImage(toggleInImages.get(root.getId()));
    }

    private void toggleOut(HBox root, Rectangle rectangle, ImageView imageView) {
        root.setStyle("-fx-background-color:transparent;");
        rectangle.setFill(Paint.valueOf("#fafafa00"));
        imageView.setImage(toggleOutImages.get(root.getId()));
    }
}
