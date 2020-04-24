package sample;

import domain.ToolsJDBCDoa;
import domain.UpgradesJDBCDoa;
import domain.UserJDBCDoa;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    List<Tools> toolsList = new ArrayList<>();
    String username;
    Stage fenster;
    Scene login, scene1, registartion;
    Label label_powerups1, label_balance, label_moneyPerSecond, label_title, label_tools, label_tool1, label_level1, label_tool_price,
            label_login_username, label_login_password, label_login_title, label_login_verification,
            label_registration_username, label_registration_password1, label_registration_password2, label_registration_title, label_registration_verification;
    Button btn_powerups1,btn_powerups2, btn_powerups3, btn_powerups4, btn_powerups5, btn_powerups6, btn_powerups7,
            btn_powerups8, btn_powerups9, btn_powerups10, btn_powerups11, btn_powerups12,  btn_Gibb, btn_tool1, btn_save, btn_exit,
            btn_login, btn_login_registration, btn_registration, btn_registration_login;
    TextField username_login, username_registration;
    PasswordField password_login, password_registration1, password_registration2;

    /*-----------------------------------
          create Objects from JDBC's
    -----------------------------------*/
    UserJDBCDoa User = new UserJDBCDoa();
    UpgradesJDBCDoa Upgrades = new UpgradesJDBCDoa();
    ToolsJDBCDoa Tools = new ToolsJDBCDoa();
    Gibb game = new Gibb(0);

    public void start(Stage primaryStage) throws Exception{

        /* Login */
        GridPane grid_login = new GridPane();
        label_login_title = new Label("Anmelden");
        label_login_username = new Label("Benutzername: ");
        username_login = new TextField();
        label_login_password = new Label("Passwort: ");
        password_login = new PasswordField();
        label_login_verification = new Label();
        btn_login = new Button("Einloggen");
        btn_login_registration = new Button("Noch keinen Account erstellt?");
        grid_login.add(label_login_title, 0,0);
        grid_login.add(label_login_username, 0,1);
        grid_login.add(username_login, 1,1);
        grid_login.add(label_login_password, 0,2);
        grid_login.add(password_login, 1,2);
        grid_login.add(label_login_verification, 0,3);
        grid_login.add(btn_login, 0,4);
        grid_login.add(btn_login_registration, 0,5);
        grid_login.setColumnSpan(label_login_title, 400);
        grid_login.setColumnSpan(label_login_verification, 400);
        grid_login.setColumnSpan(btn_login_registration, 400);
        grid_login.setPadding(new Insets(5, 20, 20, 20));
        grid_login.setVgap(7);
        grid_login.getStyleClass().add("grid_login");
        label_login_username.setPadding(new Insets(0,15,0,0));
        label_login_password.setPadding(new Insets(0,15,0,0));
        label_login_title.setFont(Font.font("Helvetica", 20));
        label_login_verification.setFont(Font.font("Helvetica", 0));
        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (event.getSource() == btn_login){
                    if (username_login.getText().isEmpty() || username_login.getText() == null || password_login.getText().isEmpty() || password_login.getText() == null){
                        label_login_verification.setText("Der Name und/oder Passwort sind leer");
                        label_login_verification.setTextFill(Color.web("red"));
                        label_login_verification.setFont(Font.font("Helvetica", 12));
                    } else {
                        if (User.checkPassword(username_login.getText(), password_login.getText())) {
                            toolsList = Tools.loadTools(username_login.getText());
                            for (Tools tool : toolsList) {
                                if (tool.isStatus() == true) {
                                    tool.loadMultiplier(username_login.getText());
                                    tool.loadMoneyPerSecond(username_login.getText());
                                    System.out.println(tool.getName()+" Level "+tool.getLevel()+" Money: "+tool.getMoneyPerSecond()+" Multiplier "+tool.getMultiplier()+" status "+tool.isStatus());
                                }

                            }
                            username = username_login.getText();
                            game.loadBalance(username);
                            fenster.setScene(scene1());
                            fenster.show();
                        } else {
                            label_login_verification.setText("Der Name und/oder Passwort sind falsch");
                            label_login_verification.setTextFill(Color.web("red"));
                            label_login_verification.setFont(Font.font("Helvetica", 12));
                        }
                    }
                }
            }
        });
        btn_login_registration.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (event.getSource() == btn_login_registration) {
                    fenster.setScene(registartion);
                    fenster.show();
                }
            }

        });


        /* Registration */
        GridPane grid_registration = new GridPane();
        label_registration_title = new Label("Registrieren");
        label_registration_username = new Label("Benutzername: ");
        username_registration = new TextField();
        label_registration_password1 = new Label("Passwort: ");
        label_registration_password2 = new Label("Passwort wiederholen: ");
        password_registration1 = new PasswordField();
        password_registration2 = new PasswordField();
        label_registration_verification = new Label();
        btn_registration = new Button("Registrieren");
        btn_registration_login = new Button("Bereits einen Account erstellt?");
        grid_registration.add(label_registration_title, 0,0);
        grid_registration.add(label_registration_username, 0,1);
        grid_registration.add(username_registration, 1,1);
        grid_registration.add(label_registration_password1, 0,2);
        grid_registration.add(password_registration1, 1,2);
        grid_registration.add(label_registration_password2, 0,3);
        grid_registration.add(password_registration2, 1,3);
        grid_registration.add(label_registration_verification, 0,4);
        grid_registration.add(btn_registration, 0,5);
        grid_registration.add(btn_registration_login, 0,6);
        grid_registration.setColumnSpan(label_registration_title, 400);
        grid_registration.setColumnSpan(label_registration_verification, 400);
        grid_registration.setColumnSpan(btn_registration_login, 400);
        grid_registration.setPadding(new Insets(5, 20, 20, 20));
        grid_registration.setVgap(7);
        grid_registration.getStyleClass().add("grid_registration");
        label_registration_username.setPadding(new Insets(0,15,0,0));
        label_registration_password1.setPadding(new Insets(0,15,0,0));
        label_registration_password2.setPadding(new Insets(0,15,0,0));
        label_registration_title.setFont(Font.font("Helvetica", 20));
        label_registration_verification.setFont(Font.font("Helvetica", 0));
        btn_registration.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (event.getSource() == btn_registration){
                    if (username_registration.getText().isEmpty() || username_registration.getText() == null || password_registration1.getText().isEmpty() ||
                            password_registration1.getText() == null || password_registration2.getText().isEmpty() || password_registration2.getText() == null){
                        label_registration_verification.setText("Der Name und/oder Passwort sind leer");
                        label_registration_verification.setTextFill(Color.web("red"));
                        label_registration_verification.setFont(Font.font("Helvetica", 12));
                    }
                    else if(!password_registration1.getText().equals(password_registration2.getText())){
                        System.out.println(password_registration1.getText());
                        System.out.println(password_registration2.getText());
                        label_registration_verification.setText("Die Passwörter stimmen nicht überein.");
                        label_registration_verification.setTextFill(Color.web("red"));
                        label_registration_verification.setFont(Font.font("Helvetica", 12));
                    }
                    else if(password_registration1.getText().equals(password_registration2.getText())){
                        if (User.checkUsername(username_registration.getText())) {
                            User.registrationUser(username_registration.getText(), password_registration1.getText(), 0);
                            int User_ID = User.getUserID(username_registration.getText());
                            for (int i = 1; i <= 10; i++) {
                                Tools.registrateTools(User_ID, i, 0, false);
                            }
                            for (int i = 1; i <= 40; i++) {
                                Upgrades.registrateUpgrades(User_ID, i, false);
                            }
                            System.out.println(User_ID);
                            fenster.setScene(scene1());
                            fenster.show();
                        } else {
                            label_registration_verification.setText("Der Benutzername existiert bereits.");
                            label_registration_verification.setTextFill(Color.web("red"));
                            label_registration_verification.setFont(Font.font("Helvetica", 12));
                        }
                    }
                }
            }
        });
        btn_registration_login.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (event.getSource() == btn_registration_login) {
                    fenster.setScene(login);
                    fenster.show();
                }
            }
        });



        login = new Scene(grid_login, 300,230);
        registartion = new Scene(grid_registration, 340, 250);
        fenster = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Gibb Clicker");
        primaryStage.setScene(login);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        ToolsJDBCDoa Tools = new ToolsJDBCDoa();
        /*Timer timer = new Timer();
        timer.schedule(addMoney(),200);*/
    }

    public Scene scene1() {

        /* Grids */
        GridPane grid_main = new GridPane();
        GridPane grid_center = new GridPane();
        GridPane grid_tools = new GridPane();
        GridPane grid_powerups = new GridPane();
        GridPane grid_stop = new GridPane();
        ScrollPane scroll_tools = new ScrollPane();
        grid_main.setMinWidth(1500);
        grid_main.setMaxWidth(1500);
        grid_main.setMaxHeight(750);
        grid_main.setMinHeight(750);




        /* Power Ups */

        label_powerups1 = new Label("Powerups");
        int row = 1;
        float iButFloat = 0;
        List<Button> powerupsList = new ArrayList<>();
        for(int i = 0; i < Upgrades.loadUpgrades().size(); i++){
            if(iButFloat / 5 % 1 == 0){
                row++;
            }
            Button powerup = new Button(Upgrades.loadUpgrades().get(i).getName());
            powerup.setPrefWidth(80);
            powerup.setPrefHeight(80);
            grid_powerups.add(powerup, i % 5, row);
            powerupsList.add(powerup);
            iButFloat = iButFloat + 1;
            switch (Upgrades.loadUpgrades().get(i).getTool_ID()){
                case 1:
                    powerup.getStyleClass().add("powerup_Smartlearn");
                    break;
                case 2:
                    powerup.getStyleClass().add("powerup_VMs");
                    break;
                case 3:
                    powerup.getStyleClass().add("powerup_Lehrer");
                    break;
                case 4:
                    powerup.getStyleClass().add("powerup_Kantine");
                    break;
                case 5:
                    powerup.getStyleClass().add("powerup_Pcs");
                    break;
                case 6:
                    powerup.getStyleClass().add("powerup_Heizung");
                    break;
                case 7:
                    powerup.getStyleClass().add("powerup_Klimaanlage");
                    break;
                case 8:
                    powerup.getStyleClass().add("powerup_WCs");
                    break;
                case 9:
                    powerup.getStyleClass().add("powerup_Klangbruecke");
                    break;
                case 10:
                    powerup.getStyleClass().add("powerup_Russische_Reviews");
                    break;

            }

        }



        grid_powerups.add(label_powerups1,0,0);
        label_powerups1.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label_powerups1, 0.0);
        AnchorPane.setRightAnchor(label_powerups1, 0.0);
        label_powerups1.setAlignment(Pos.CENTER);
        grid_main.setColumnSpan(label_powerups1, 400);
        grid_powerups.setMinWidth(400);
        grid_powerups.setMaxWidth(400);
        grid_powerups.setMaxHeight(700);
        grid_powerups.setMinHeight(700);



        /* Center */
        label_balance = new Label(game.getBalance() + " $");
        btn_Gibb = new Button();
        btn_Gibb.setPrefHeight(800);
        btn_Gibb.setPrefWidth(1500);
        btn_Gibb.getStyleClass().add("btn_Gibb");
        label_moneyPerSecond =  new Label(calcMoneyPerSecond() + "$ pro Sekunde");
        label_title = new Label("GIBB Clicker");
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
        label_moneyPerSecond.setFont(Font.font("Helvetica", 20));
        label_powerups1.setFont(Font.font("Helvetica", 27));
        grid_center.setMinWidth(700);
        grid_center.setMaxWidth(700);
        grid_center.setMaxHeight(700);
        grid_center.setMinHeight(700);



        /* Tools */
        label_tools = new Label("Tools");
        List<Button> toolsButton = new ArrayList<>();
        List<Label> toolsLabelName = new ArrayList<>();
        List<Label> toolsLabelLevel = new ArrayList<>();
        List<Label> toolsLabelPrice = new ArrayList<>();


        //Alle Tools generieren
        for (int i = 0; i < Tools.loadTools(username).size(); i++) {
            Button toolButton = new Button(Tools.loadTools(username_login.getText()).get(i).getName());
            grid_tools.add(toolButton,0,i+1);
            switch (Tools.loadTools(username).get(i).getToolID()){
                case 1:
                    toolButton.getStyleClass().add("powerup_Smartlearn");
                    break;
                case 2:
                    toolButton.getStyleClass().add("powerup_VMs");
                    break;
                case 3:
                    toolButton.getStyleClass().add("powerup_Lehrer");
                    break;
                case 4:
                    toolButton.getStyleClass().add("powerup_Kantine");
                    break;
                case 5:
                    toolButton.getStyleClass().add("powerup_Pcs");
                    break;
                case 6:
                    toolButton.getStyleClass().add("powerup_Heizung");
                    break;
                case 7:
                    toolButton.getStyleClass().add("powerup_Klimaanlage");
                    break;
                case 8:
                    toolButton.getStyleClass().add("powerup_WCs");
                    break;
                case 9:
                    toolButton.getStyleClass().add("powerup_Klangbruecke");
                    break;
                case 10:
                    toolButton.getStyleClass().add("powerup_Russische_Reviews");
                    break;

            }
            toolsButton.add(toolButton);

            Label toolLabelName = new Label(Tools.loadTools(username_login.getText()).get(i).getName());
            grid_tools.add(toolLabelName, 1,i+1);
            toolLabelName.getStyleClass().add("label_tool_name");
            toolsLabelName.add(toolLabelName);

            Label toolLabelLevel = new Label(Integer.toString(Tools.loadTools(username_login.getText()).get(i).getLevel()));
            grid_tools.add(toolLabelLevel,2,i+1);
            toolLabelLevel.getStyleClass().add("label_level");
            toolsLabelLevel.add(toolLabelLevel);

            Label toolLabelPrice = new Label(Long.toString(Tools.loadTools(username_login.getText()).get(i).getPrice()));
            grid_tools.add(toolLabelPrice,1,i+1);
            toolLabelPrice.getStyleClass().add("label_tool_price");
            toolsLabelPrice.add(toolLabelPrice);

            toolButton.setPrefHeight(80);
            toolButton.setPrefWidth(80);
            scroll_tools.setContent(grid_tools);
            scroll_tools.setPrefHeight(700);
            scroll_tools.setPrefWidth(370);
        }

        grid_tools.add(label_tools, 0,0);
        label_tools.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label_tools, 0.0);
        AnchorPane.setRightAnchor(label_tools, 0.0);
        label_tools.setAlignment(Pos.CENTER);
        grid_main.setColumnSpan(label_tools, 400);
        label_tools.setFont(Font.font("Helvetica", 27));
        grid_tools.setMinWidth(400);
        grid_tools.setMaxWidth(400);
        grid_tools.setMaxHeight(700);
        grid_tools.setMinHeight(700);



        /* Main Grid */

        grid_main.getStyleClass().add("grid_main");
        btn_save = new Button("Spiel speichern");
        btn_exit = new Button("Spiel beenden");
        grid_stop.add(btn_save,0,0);
        grid_stop.add(btn_exit,1,0);
        grid_main.add(label_title, 0, 0);
        grid_main.add(grid_powerups, 0,1);
        grid_main.add(grid_center, 1,1);
        grid_main.add(grid_stop,1,2);
        grid_main.add(scroll_tools,2,1);
        grid_stop.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(grid_stop, 0.0);
        AnchorPane.setRightAnchor(grid_stop, 0.0);
        grid_stop.setAlignment(Pos.CENTER);
        grid_stop.setHgap(10);
        btn_exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        grid_main.setColumnSpan(label_title, 1500);


        scene1 = new Scene(grid_main, 1500, 775);
        scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        return scene1;
    }

    public double calcMoneyPerSecond() {
        double money = 0;
        for (Tools tool : toolsList) {
            money = money + tool.getMoneyPerSecond();
        }
        return money;
    }

    public void addMoney() {
        game.setBalance(calcMoneyPerSecond()/5);
    }

}
