package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;


public class Main extends Application {
    Stage fenster;
    Scene scene1;
    Label label_powerups1, label_balance, label_moneyPerSecond, label_title;
    Button btn_powerups1,btn_powerups2, btn_powerups3, btn_powerups4, btn_powerups5, btn_powerups6, btn_powerups7, btn_powerups8, btn_powerups9, btn_powerups10, btn_powerups11, btn_powerups12,  btn_Gibb;

    public void start(Stage primaryStage) throws Exception{

        GridPane grid_main = new GridPane();
        GridPane grid_powerups = new GridPane();
        GridPane grid_center = new GridPane();
        GridPane grid_tools = new GridPane();

        label_powerups1 = new Label("Powerups");
        btn_powerups1 = new Button("Test");
        btn_powerups2 = new Button("Test");
        btn_powerups3 = new Button("Test");
        btn_powerups4 = new Button("Test");
        btn_powerups5 = new Button("Test");
        btn_powerups6 = new Button("Test");
        btn_powerups7 = new Button("Test");
        btn_powerups8 = new Button("Test");
        btn_powerups9 = new Button("Test");
        btn_powerups10 = new Button("Test");
        btn_powerups11 = new Button("Test");
        btn_powerups12 = new Button("Test");

        btn_powerups1.setPrefWidth(80);
        btn_powerups2.setPrefWidth(80);
        btn_powerups3.setPrefWidth(80);
        btn_powerups4.setPrefWidth(80);
        btn_powerups5.setPrefWidth(80);
        btn_powerups6.setPrefWidth(80);
        btn_powerups7.setPrefWidth(80);
        btn_powerups8.setPrefWidth(80);
        btn_powerups9.setPrefWidth(80);
        btn_powerups10.setPrefWidth(80);
        btn_powerups11.setPrefWidth(80);
        btn_powerups12.setPrefWidth(80);

        btn_powerups1.setPrefHeight(80);
        btn_powerups2.setPrefHeight(80);
        btn_powerups3.setPrefHeight(80);
        btn_powerups4.setPrefHeight(80);
        btn_powerups5.setPrefHeight(80);
        btn_powerups6.setPrefHeight(80);
        btn_powerups7.setPrefHeight(80);
        btn_powerups8.setPrefHeight(80);
        btn_powerups9.setPrefHeight(80);
        btn_powerups10.setPrefHeight(80);
        btn_powerups11.setPrefHeight(80);
        btn_powerups12.setPrefHeight(80);

        label_balance = new Label(10349032 + " $");
        btn_Gibb = new Button();
        btn_Gibb.setPrefHeight(800);
        btn_Gibb.setPrefWidth(1500);
        btn_Gibb.getStyleClass().add("btn_Gibb");
        label_moneyPerSecond =  new Label(4324.5 + "$ pro Sekunde");
        label_title = new Label("GIBB Clicker");
        grid_center.setAlignment(Pos.CENTER);


        grid_powerups.add(label_powerups1, 0,0);
        grid_powerups.add(btn_powerups1, 0, 1);
        grid_powerups.add(btn_powerups2, 1, 1);
        grid_powerups.add(btn_powerups3, 2, 1);
        grid_powerups.add(btn_powerups4, 3, 1);
        grid_powerups.add(btn_powerups5, 4, 1);
        grid_powerups.add(btn_powerups6, 0, 2);
        grid_powerups.add(btn_powerups7, 1, 2);
        grid_powerups.add(btn_powerups8, 2, 2);
        grid_powerups.add(btn_powerups9, 3, 2);
        grid_powerups.add(btn_powerups10, 4, 2);
        grid_powerups.add(btn_powerups11, 0, 3);
        grid_powerups.add(btn_powerups12, 1, 3);

        grid_center.add(label_moneyPerSecond, 0, 1);
        grid_center.add(btn_Gibb, 0, 2);
        grid_center.add(label_balance, 0, 0);

        label_title.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label_title, 0.0);
        AnchorPane.setRightAnchor(label_title, 0.0);
        label_title.setAlignment(Pos.CENTER);
        label_moneyPerSecond.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label_moneyPerSecond, 0.0);
        AnchorPane.setRightAnchor(label_moneyPerSecond, 0.0);
        label_moneyPerSecond.setAlignment(Pos.CENTER);
        label_balance.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label_balance, 0.0);
        AnchorPane.setRightAnchor(label_balance, 0.0);
        label_balance.setAlignment(Pos.CENTER);
        label_title.setFont(Font.font("Helvetica", 30));
        label_balance.setFont(Font.font("Helvetica", 60));

        grid_main.add(label_title, 0, 0);
        grid_main.add(grid_powerups, 0,1);
        grid_main.add(grid_center, 1,1);
        grid_main.add(grid_tools, 2, 1);
        grid_main.setColumnSpan(label_title, 1500);

        grid_main.setMinWidth(1500);
        grid_main.setMaxWidth(1500);
        grid_main.setMaxHeight(1000);
        grid_main.setMinHeight(1000);

        grid_powerups.setMinWidth(400);
        grid_powerups.setMaxWidth(400);
        grid_powerups.setMaxHeight(1000);
        grid_powerups.setMinHeight(1000);

        grid_center.setMinWidth(700);
        grid_center.setMaxWidth(700);
        grid_center.setMaxHeight(1000);
        grid_center.setMinHeight(1000);

        grid_tools.setMinWidth(400);
        grid_tools.setMaxWidth(400);
        grid_tools.setMaxHeight(1000);
        grid_tools.setMinHeight(1000);

        scene1 = new Scene(grid_main, 1500, 1000);
        scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        fenster = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Gibb Clicker");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
