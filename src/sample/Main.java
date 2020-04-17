package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application implements EventHandler<ActionEvent> {

    Stage fenster;
    Scene szene1;
    TextField textField1;
    PasswordField passwordField1;

    public void start(Stage primaryStage) throws Exception {

        fenster = primaryStage;

        GridPane grid1 = new GridPane();

        primaryStage.setMaximized(true);

        primaryStage.setScene(szene1);
        primaryStage.setTitle("Gibb Clicker");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}

