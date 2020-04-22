package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {
    Stage fenster;
    Scene scene1;
    Label label_powerups1, label_balance, label_moneyPerSecond, label_title, label_tools, label_tool1, label_level1, label_tool_price;
    Button btn_powerups1,btn_powerups2, btn_powerups3, btn_powerups4, btn_powerups5, btn_powerups6, btn_powerups7,
            btn_powerups8, btn_powerups9, btn_powerups10, btn_powerups11, btn_powerups12,  btn_Gibb, btn_tool1, btn_save, btn_exit;
    Image icon_1;

    public void start(Stage primaryStage) throws Exception{

        /* Grids */
        GridPane grid_main = new GridPane();
        GridPane grid_center = new GridPane();
        GridPane grid_tools = new GridPane();
        GridPane grid_powerups = new GridPane();


        grid_main.setMinWidth(1500);
        grid_main.setMaxWidth(1500);
        grid_main.setMaxHeight(750);
        grid_main.setMinHeight(750);





        btn_save = new Button("Spiel speichern");
        btn_exit = new Button("Spiel beenden");


        /* Power Ups */
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


        label_balance = new Label(10349032 + " $");
        btn_Gibb = new Button();
        btn_Gibb.setPrefHeight(800);
        btn_Gibb.setPrefWidth(1500);
        btn_Gibb.getStyleClass().add("btn_Gibb");
        label_moneyPerSecond =  new Label(4324.5 + "$ pro Sekunde");
        label_title = new Label("GIBB Clicker");

        label_tools = new Label("Tools");
        btn_tool1 = new Button();
        label_tool1 = new Label("Klangbrücke");
        label_level1 = new Label("0");
        label_tool_price = new Label(10 +" $");




        grid_center.add(label_moneyPerSecond, 0, 1);
        grid_center.add(btn_Gibb, 0, 2);
        grid_center.add(label_balance, 0, 0);

        grid_tools.add(label_tools, 0,0);
        label_tools.getStyleClass().add("label_tools");
        grid_tools.add(btn_tool1, 0,1);
        btn_tool1.getStyleClass().add("btn_tool");
        grid_tools.add(label_tool1, 1,1);
        label_tool1.getStyleClass().add("label_tool_name");
        grid_tools.add(label_level1, 2,1);
        label_level1.getStyleClass().add("label_level");
        grid_tools.add(label_tool_price, 1,1);
        label_tool_price.getStyleClass().add("label_tool_price");


        label_tools.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label_tools, 0.0);
        AnchorPane.setRightAnchor(label_tools, 0.0);
        label_tools.setAlignment(Pos.CENTER);
        grid_main.setColumnSpan(label_tools, 400);


        label_powerups1.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label_powerups1, 0.0);
        AnchorPane.setRightAnchor(label_powerups1, 0.0);
        label_powerups1.setAlignment(Pos.CENTER);
        grid_main.setColumnSpan(label_powerups1, 400);



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
        label_moneyPerSecond.setFont(Font.font("Helvetica", 20));
        label_powerups1.setFont(Font.font("Helvetica", 27));
        label_tools.setFont(Font.font("Helvetica", 27));



        grid_powerups.setMinWidth(400);
        grid_powerups.setMaxWidth(400);
        grid_powerups.setMaxHeight(700);
        grid_powerups.setMinHeight(700);

        grid_center.setMinWidth(700);
        grid_center.setMaxWidth(700);
        grid_center.setMaxHeight(700);
        grid_center.setMinHeight(700);

        grid_tools.setMinWidth(400);
        grid_tools.setMaxWidth(400);
        grid_tools.setMaxHeight(700);
        grid_tools.setMinHeight(700);

        /* Main Grid */

        grid_main.getStyleClass().add("grid_main");
        grid_main.add(label_title, 0, 0);
        grid_main.add(grid_powerups, 0,1);
        grid_main.add(grid_center, 1,1);
        grid_main.add(grid_tools, 2, 1);
        grid_main.add(btn_save,2,2);
        btn_save.getStyleClass().add("btn_save");
        grid_main.setColumnSpan(label_title, 1500);

        scene1 = new Scene(grid_main, 1500, 775);
        scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        fenster = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Gibb Clicker");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }


    public static void main(String[] args) {
        /*ToolsJDBCDoa Tools = new ToolsJDBCDoa();
        for (Tools tool : Tools.loadTools()) {
            tool.loadMoneyPerSecond();
            tool.loadMultiplier();
            System.out.println(tool.getMoneyPerSecond()+tool.getMultiplier());
        }*/
    }
}
