package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
    Stage fenster;
    Scene scene1;

    public void start(Stage primaryStage) throws Exception{

        GridPane grid_main = new GridPane();
        GridPane grid_powerups = new GridPane();
        GridPane grid_center = new GridPane();
        GridPane grid_tools = new GridPane();

        Scene scene1 = new Scene(grid_main);
        scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        grid_main.add(grid_powerups, 0,0);
        grid_main.add(grid_center, 1,0 );
        grid_main.add(grid_tools, 2, 0);
        grid_main.setGridLinesVisible(true);


        fenster = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Gibb Clicker");
        primaryStage.setScene(new Scene(root, 1500, 1000));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
