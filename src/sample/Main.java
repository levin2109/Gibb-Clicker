package sample;

import domain.ToolsJDBCDoa;
import domain.UpgradesJDBCDoa;
import domain.UserJDBCDoa;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    static Stage primaryStage;
    static List<Tools> toolsList = new ArrayList<>();
    UserJDBCDoa User = new UserJDBCDoa();
    UpgradesJDBCDoa Upgrades = new UpgradesJDBCDoa();
    static ToolsJDBCDoa Tools = new ToolsJDBCDoa();
    static Gibb game = new Gibb(0);
    static GUI gui = new GUI();
    static boolean labelSet;


    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                gui.startApplication(args);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mainWork();
            }
        }).start();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    gui.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
    }

    public static void mainWork() {

        System.out.println("main");
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                toolsList = gui.getToolsList();
                if (toolsList.size() >= 1) {
                    game.addMoney(toolsList);
                    gui.updateLabels();
                    System.out.println("hobla");
                }
            }
        }, 200, 500);

    }


}
