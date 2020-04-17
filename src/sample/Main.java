package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    Stage fenster;
    Scene scene1;
    Label label_powerups;
    Button btn_powerups;

    public void start(Stage primaryStage) throws Exception{

        GridPane grid_main = new GridPane();
        GridPane grid_powerups = new GridPane();
        GridPane grid_center = new GridPane();
        GridPane grid_tools = new GridPane();

        label_powerups = new Label("Powerups");
        btn_powerups = new Button("Test");

        grid_powerups.add(label_powerups, 0,0);
        grid_powerups.add(btn_powerups, 0, 1);

        grid_main.add(grid_powerups, 0,0);
        grid_main.add(grid_center, 1,0 );
        grid_main.add(grid_tools, 2, 0);



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
