package org.graded_classes.graded_attendance.controller;

import atlantafx.base.controls.ModalPane;
import atlantafx.base.controls.Notification;
import atlantafx.base.util.Animations;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.graded_classes.graded_attendance.GradedFxmlLoader;
import org.graded_classes.graded_attendance.GradedResourceLoader;
import org.graded_classes.graded_attendance.R;
import org.graded_classes.graded_attendance.data.Formatter;
import org.graded_classes.graded_attendance.data.GradedDataLoader;
import org.graded_classes.graded_attendance.data.MessageSender;
import org.graded_classes.graded_attendance.planner.Planner;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignH;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public StackPane stackPane;
    @FXML
    HBox selectedTab;
    Tooltip tooltip;
    Stage stage;
    Node home, chat, calendar, lesson;
    Map<String, Image> toggleInImages = Map.of(
            "home", new Image(GradedResourceLoader.load("icons/home_in.svg")),
            "chat", new Image(GradedResourceLoader.load("icons/chat_in.svg")),
            "calender", new Image(GradedResourceLoader.load("icons/calendar_month_in.svg")),
            "database", new Image(GradedResourceLoader.load("icons/database_in.svg")),
            "lesson", new Image(GradedResourceLoader.load("icons/book_in.svg")));
    Map<String, Image> toggleOutImages = Map.of(
            "home", new Image(GradedResourceLoader.load("icons/home.svg")),
            "chat", new Image(GradedResourceLoader.load("icons/chat.svg")),
            "calender", new Image(GradedResourceLoader.load("icons/calendar_month.svg")),
            "database", new Image(GradedResourceLoader.load("icons/database.svg")),
            "lesson", new Image(GradedResourceLoader.load("icons/book.svg")));
    @FXML
    ModalPane modalPane;
    GradedFxmlLoader gradedFxmlLoader = new GradedFxmlLoader();
    @FXML
    BorderPane main_view;
    public GradedDataLoader gradedDataLoader = new GradedDataLoader(this);
    MessageSender messageSender;

    public MainController(Stage stage) {
        this.stage = stage;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        home = gradedFxmlLoader.createView(R.home_layout, new HomeController(modalPane,
                gradedDataLoader, this));
        chat = gradedFxmlLoader.createView(R.chat_layout, new ChatController());
        calendar = gradedFxmlLoader.createView(R.calendar_layout, new CalenderController());
        main_view.setCenter(navigateView("home"));
        tooltip = new Tooltip(Formatter.format(selectedTab.getId()));
        Tooltip.install(selectedTab, tooltip);
        messageSender = new MessageSender(gradedDataLoader.databaseLoader, this);
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
        Tooltip.uninstall(selectedTab, tooltip);
        selectedTab = root;
        tooltip.setText(Formatter.format(selectedTab.getId()));
        Tooltip.install(selectedTab, tooltip);
    }

    private Node navigateView(String id) {
        return switch (id) {
            case "home" -> home;
            case "chat" -> chat;
            case "calender" -> calendar;
            case "database" -> gradedFxmlLoader.createView(R.database_layout, new DataBaseController(this));
            case "lesson" -> gradedFxmlLoader.createView(R.lesson_planner,new Planner(gradedDataLoader));
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

    public void sendNotification(String message, String styles) {
        var msg = new Notification(message, new FontIcon(MaterialDesignH.HELP_CIRCLE_OUTLINE));
        msg.getStyleClass().add(styles);
        msg.setPrefHeight(Region.USE_PREF_SIZE);
        msg.setMaxHeight(Region.USE_PREF_SIZE);
        StackPane.setAlignment(msg, Pos.TOP_RIGHT);
        StackPane.setMargin(msg, new Insets(10, 10, 0, 0));
        msg.setOnClose(_ -> {
            var out = Animations.slideOutUp(msg, Duration.millis(250));
            out.setOnFinished(_ -> stackPane.getChildren().remove(msg));
            out.playFromStart();
        });
        var in = Animations.slideInDown(msg, Duration.millis(250));
        if (!stackPane.getChildren().contains(msg)) {
            stackPane.getChildren().addAll(msg);
        }
        in.playFromStart();
    }

    public void onSetting(MouseEvent mouseEvent) {

    }
}
