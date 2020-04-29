package sample;

import domain.SaveJDBCDoa;
import domain.ToolsJDBCDoa;
import domain.UpgradesJDBCDoa;
import domain.UserJDBCDoa;
import javafx.animation.AnimationTimer;
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

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class GUI extends Application {

    /*-----------------------------------------------------
                 GUI Bauteile & Deklarationen
    ------------------------------------------------------*/
    static List<Tools> toolsList = new ArrayList<>();
    static List<Upgrades> upgradesList = new ArrayList<>();
    String username;
    Stage window;
    Scene login, scene1, registartion;
    Label label_powerups1, label_balance, label_moneyPerSecond, label_title, label_tools, label_login_username, label_login_password, label_login_title, label_login_verification,
            label_registration_username, label_registration_password1, label_registration_password2, label_registration_title, label_registration_verification;
    Button btn_Gibb, btn_save, btn_exit,
            btn_login, btn_login_registration, btn_registration, btn_registration_login;
    TextField username_login, username_registration;
    PasswordField password_login, password_registration1, password_registration2;
    int ID_User;

    /*-----------------------------------
          create Objects from JDBC's
    -----------------------------------*/
    SaveJDBCDoa Save = new SaveJDBCDoa();
    UserJDBCDoa User = new UserJDBCDoa();
    UpgradesJDBCDoa Upgrades = new UpgradesJDBCDoa();
    ToolsJDBCDoa Tools = new ToolsJDBCDoa();
    static Gibb game = new Gibb(0);
    Main main = new Main();

    /*---------------------------------
                  methods
     --------------------------------*/
    public void startApplication(String[] args) {
        launch(args);
    }

    public void setScene() {
        if (scene1 == null) {
            Scene scene = scene1();
            window.setScene(scene);
        } else {
            window.setScene(scene1);
        }
        window.show();
    }

    public void start(Stage primaryStage) throws Exception {

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
        grid_login.add(label_login_title, 0, 0);
        grid_login.add(label_login_username, 0, 1);
        grid_login.add(username_login, 1, 1);
        grid_login.add(label_login_password, 0, 2);
        grid_login.add(password_login, 1, 2);
        grid_login.add(label_login_verification, 0, 3);
        grid_login.add(btn_login, 0, 4);
        grid_login.add(btn_login_registration, 0, 5);
        grid_login.setColumnSpan(label_login_title, 400);
        grid_login.setColumnSpan(label_login_verification, 400);
        grid_login.setColumnSpan(btn_login_registration, 400);
        grid_login.setPadding(new Insets(5, 20, 20, 20));
        grid_login.setVgap(7);
        grid_login.getStyleClass().add("grid_login");
        label_login_username.setPadding(new Insets(0, 15, 0, 0));
        label_login_password.setPadding(new Insets(0, 15, 0, 0));
        label_login_title.setFont(Font.font("Helvetica", 20));
        label_login_verification.setFont(Font.font("Helvetica", 0));
        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (event.getSource() == btn_login) {
                    if (username_login.getText().isEmpty() || username_login.getText() == null || password_login.getText().isEmpty() || password_login.getText() == null) {
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
                                }

                            }
                            username = username_login.getText();
                            ID_User = User.getUserID(username);
                            game.loadBalance(username);
                            primaryStage.setScene(scene1());
                            primaryStage.show();
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
                    primaryStage.setScene(registartion);
                    primaryStage.show();
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
        grid_registration.add(label_registration_title, 0, 0);
        grid_registration.add(label_registration_username, 0, 1);
        grid_registration.add(username_registration, 1, 1);
        grid_registration.add(label_registration_password1, 0, 2);
        grid_registration.add(password_registration1, 1, 2);
        grid_registration.add(label_registration_password2, 0, 3);
        grid_registration.add(password_registration2, 1, 3);
        grid_registration.add(label_registration_verification, 0, 4);
        grid_registration.add(btn_registration, 0, 5);
        grid_registration.add(btn_registration_login, 0, 6);
        grid_registration.setColumnSpan(label_registration_title, 400);
        grid_registration.setColumnSpan(label_registration_verification, 400);
        grid_registration.setColumnSpan(btn_registration_login, 400);
        grid_registration.setPadding(new Insets(5, 20, 20, 20));
        grid_registration.setVgap(7);
        grid_registration.getStyleClass().add("grid_registration");
        label_registration_username.setPadding(new Insets(0, 15, 0, 0));
        label_registration_password1.setPadding(new Insets(0, 15, 0, 0));
        label_registration_password2.setPadding(new Insets(0, 15, 0, 0));
        label_registration_title.setFont(Font.font("Helvetica", 20));
        label_registration_verification.setFont(Font.font("Helvetica", 0));
        btn_registration.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (event.getSource() == btn_registration) {
                    if (username_registration.getText().isEmpty() || username_registration.getText() == null || password_registration1.getText().isEmpty() ||
                            password_registration1.getText() == null || password_registration2.getText().isEmpty() || password_registration2.getText() == null) {
                        label_registration_verification.setText("Der Name und/oder Passwort sind leer");
                        label_registration_verification.setTextFill(Color.web("red"));
                        label_registration_verification.setFont(Font.font("Helvetica", 12));
                    } else if (!password_registration1.getText().equals(password_registration2.getText())) {
                        System.out.println(password_registration1.getText());
                        System.out.println(password_registration2.getText());
                        label_registration_verification.setText("Die Passwörter stimmen nicht überein.");
                        label_registration_verification.setTextFill(Color.web("red"));
                        label_registration_verification.setFont(Font.font("Helvetica", 12));
                    } else if (password_registration1.getText().equals(password_registration2.getText())) {
                        if (User.checkUsername(username_registration.getText())) {
                            User.registrationUser(username_registration.getText(), password_registration1.getText(), 0);
                            int User_ID = User.getUserID(username_registration.getText());
                            for (int i = 1; i <= 10; i++) {
                                Tools.registrateTools(User_ID, i, 0, false);
                            }
                            for (int i = 1; i <= 40; i++) {
                                Upgrades.registrateUpgrades(User_ID, i, false);
                            }
                            ID_User = User.getUserID(username_registration.getText());
                            primaryStage.setScene(scene1());
                            primaryStage.show();
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
                    primaryStage.setScene(login);
                    primaryStage.show();
                }
            }
        });
        login = new Scene(grid_login, 300, 230);
        registartion = new Scene(grid_registration, 340, 250);
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        if (primaryStage != null) {
            primaryStage.setTitle("Gibb Clicker");
            primaryStage.setScene(login);
            primaryStage.show();
        }


    }

    public Scene scene1() {

        /* Grids */
        GridPane grid_main = new GridPane();
        GridPane grid_center = new GridPane();
        GridPane grid_tools = new GridPane();
        GridPane grid_powerups = new GridPane();
        GridPane grid_stop = new GridPane();
        ScrollPane scroll_tools = new ScrollPane();
        ScrollPane scroll_powerups = new ScrollPane();
        grid_main.setMinWidth(1500);
        grid_main.setMaxWidth(1500);
        grid_main.setMaxHeight(750);
        grid_main.setMinHeight(750);



        /* Power Ups */

        label_powerups1 = new Label("Powerups");
        int row = 1;
        float iButFloat = 0;
        List<Button> powerupsList = new ArrayList<>();
        for (int i = 0; i < Upgrades.loadUpgrades().size(); i++) {
            if (iButFloat / 5 % 1 == 0) {
                row++;
            }

            Button powerup = new Button();
            powerup.setMinWidth(70);
            powerup.setMinHeight(70);
            grid_powerups.setHgap(9);
            grid_powerups.setVgap(9);
            grid_powerups.add(powerup, i % 5, row);
            powerupsList.add(powerup);
            iButFloat = iButFloat + 1;
            String tooltip_text_name = "Name: " + Upgrades.loadUpgrades().get(i).getName();
            String tooltip_text_price = "Preis: " + NumberFormat.getIntegerInstance().format(Upgrades.loadUpgrades().get(i).getPrice()) + " CHF";
            String tooltip_text_multiplier = "Dein/e " + Tools.getToolName(Upgrades.loadUpgrades().get(i).getTool_ID()) + " nimmt " + Upgrades.loadUpgrades().get(i).getMultiplier() + "-mal so viel Geld ein.";
            Tooltip tooltipPowerup = TooltipBuilder.create().text(tooltip_text_name + "\n" + tooltip_text_price + "\n" + tooltip_text_multiplier).prefWidth(300).wrapText(true).build();
            powerup.setTooltip(tooltipPowerup);
            switch (Upgrades.loadUpgrades().get(i).getTool_ID()) {
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


        grid_powerups.add(label_powerups1, 0, 0);
        label_powerups1.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label_powerups1, 0.0);
        AnchorPane.setRightAnchor(label_powerups1, 0.0);
        label_powerups1.setAlignment(Pos.CENTER);
        grid_main.setColumnSpan(label_powerups1, 400);
        grid_powerups.setMinWidth(400);
        grid_powerups.setMaxWidth(400);
        scroll_powerups.setContent(grid_powerups);
        scroll_powerups.setPrefWidth(400);
        scroll_powerups.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll_powerups.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scroll_powerups.setStyle("-fx-background-color: #FFFFFF;");
        grid_powerups.setStyle("-fx-background-color: #FFFFFF;");
        grid_powerups.setPadding(new Insets(0, 20, 0, 0));


        /* Center */
        label_balance = new Label(game.getBalance() + " $");
        btn_Gibb = new Button();
        btn_Gibb.setPrefHeight(800);
        btn_Gibb.setPrefWidth(1500);
        btn_Gibb.getStyleClass().add("btn_Gibb");

        label_moneyPerSecond = new Label(game.calcMoneyPerSecond(toolsList) + "$ pro Sekunde");
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

        //function for the GIBB button
        btn_Gibb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.click();
            }
        });


        /* Tools */
        label_tools = new Label("Tools");
        List<Button> toolsButton = new ArrayList<>();
        List<Label> toolsLabelName = new ArrayList<>();
        List<Label> toolsLabelLevel = new ArrayList<>();
        List<Label> toolsLabelPrice = new ArrayList<>();

        //Alle Tools generieren
        for (int i = 0; i < Tools.loadTools(username).size(); i++) {
            Button toolButton = new Button();
            grid_tools.add(toolButton, 0, i + 1);
            switch (Tools.loadTools(username).get(i).getToolID()) {
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
            toolButton.setPrefWidth(80);
            toolButton.setPrefHeight(80);

            Label toolLabelName = new Label(Tools.loadTools(username_login.getText()).get(i).getName());
            grid_tools.add(toolLabelName, 1, i + 1);
            toolLabelName.getStyleClass().add("label_tool_name");
            toolsLabelName.add(toolLabelName);

            Label toolLabelLevel = new Label(Integer.toString(Tools.loadTools(username_login.getText()).get(i).getLevel()));
            grid_tools.add(toolLabelLevel, 2, i + 1);
            toolLabelLevel.getStyleClass().add("label_level");
            toolsLabelLevel.add(toolLabelLevel);

            Label toolLabelPrice = new Label(Long.toString(Tools.loadTools(username_login.getText()).get(i).getPrice() + (Tools.loadTools(username).get(i).getLevel() * Tools.loadTools(username).get(i).getPricePerLevel())));
            grid_tools.add(toolLabelPrice, 1, i + 1);
            toolLabelPrice.getStyleClass().add("label_tool_price");
            toolsLabelPrice.add(toolLabelPrice);
        }

        //make the function for every tool
        for (Button btn : toolsButton) {
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String c = String.valueOf(btn.getStyleClass());
                    System.out.println(c);
                    switch (c) {
                        case "button powerup_Smartlearn":
                            toolsList.get(0).buy(game);
                            toolsLabelLevel.get(0).setText(String.valueOf(toolsList.get(0).getLevel()));
                            toolsLabelPrice.get(0).setText(String.valueOf(toolsList.get(0).getPrice()+(toolsList.get(0).getPricePerLevel()*toolsList.get(0).getLevel())));
                            break;
                        case "button powerup_VMs":
                            toolsList.get(1).buy(game);
                            toolsLabelLevel.get(1).setText(String.valueOf(toolsList.get(1).getLevel()));
                            toolsLabelPrice.get(1).setText(String.valueOf(toolsList.get(1).getPrice()+(toolsList.get(1).getPricePerLevel()*toolsList.get(1).getLevel())));
                            break;
                        case "button powerup_Lehere":
                            toolsList.get(2).buy(game);
                            toolsLabelLevel.get(2).setText(String.valueOf(toolsList.get(2).getLevel()));
                            toolsLabelPrice.get(2).setText(String.valueOf(toolsList.get(2).getPrice()+(toolsList.get(2).getPricePerLevel()*toolsList.get(2).getLevel())));
                            break;
                        case "buttno powerup_Kantine":
                            toolsList.get(3).buy(game);
                            toolsLabelLevel.get(3).setText(String.valueOf(toolsList.get(3).getLevel()));
                            toolsLabelPrice.get(3).setText(String.valueOf(toolsList.get(3).getPrice()+(toolsList.get(3).getPricePerLevel()*toolsList.get(3).getLevel())));
                            break;
                        case "button powerup_PCs":
                            toolsList.get(4).buy(game);
                            toolsLabelLevel.get(4).setText(String.valueOf(toolsList.get(4).getLevel()));
                            toolsLabelPrice.get(4).setText(String.valueOf(toolsList.get(4).getPrice()+(toolsList.get(4).getPricePerLevel()*toolsList.get(4).getLevel())));
                            break;
                        case "button powerup_Heizung":
                            toolsList.get(5).buy(game);
                            toolsLabelLevel.get(5).setText(String.valueOf(toolsList.get(5).getLevel()));
                            toolsLabelPrice.get(5).setText(String.valueOf(toolsList.get(5).getPrice()+(toolsList.get(5).getPricePerLevel()*toolsList.get(5).getLevel())));
                            break;
                        case "button powerup_Klimaanlage":
                            toolsList.get(6).buy(game);
                            toolsLabelLevel.get(6).setText(String.valueOf(toolsList.get(6).getLevel()));
                            toolsLabelPrice.get(6).setText(String.valueOf(toolsList.get(6).getPrice()+(toolsList.get(6).getPricePerLevel()*toolsList.get(6).getLevel())));
                            break;
                        case "button powerup_WCs":
                            toolsList.get(7).buy(game);
                            toolsLabelLevel.get(7).setText(String.valueOf(toolsList.get(7).getLevel()));
                            toolsLabelPrice.get(7).setText(String.valueOf(toolsList.get(7).getPrice()+(toolsList.get(7).getPricePerLevel()*toolsList.get(7).getLevel())));
                            break;
                        case "button powerup_Klangbrueche":
                            toolsList.get(8).buy(game);
                            toolsLabelLevel.get(8).setText(String.valueOf(toolsList.get(8).getLevel()));
                            toolsLabelPrice.get(8).setText(String.valueOf(toolsList.get(8).getPrice()+(toolsList.get(8).getPricePerLevel()*toolsList.get(8).getLevel())));
                            break;
                        case "button powerup_Russische_Reviews":
                            toolsList.get(9).buy(game);
                            toolsLabelLevel.get(9).setText(String.valueOf(toolsList.get(9).getLevel()));
                            toolsLabelPrice.get(9).setText(String.valueOf(toolsList.get(9).getPrice()+(toolsList.get(9).getPricePerLevel()*toolsList.get(9).getLevel())));
                            break;
                        default:
                    }
                }
            });
        }

        grid_tools.add(label_tools, 0, 0);
        label_tools.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label_tools, 0.0);
        AnchorPane.setRightAnchor(label_tools, 0.0);
        label_tools.setAlignment(Pos.CENTER);
        grid_main.setColumnSpan(label_tools, 400);
        label_tools.setFont(Font.font("Helvetica", 27));
        grid_tools.setMinWidth(400);
        grid_tools.setMaxWidth(400);
        scroll_tools.setContent(grid_tools);
        scroll_tools.setPrefWidth(400);
        scroll_tools.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll_tools.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scroll_tools.setStyle("-fx-background-color: #FFFFFF;");
        grid_tools.setStyle("-fx-background-color: #FFFFFF;");



        /* Main Grid */

        grid_main.getStyleClass().add("grid_main");
        btn_save = new Button("Spiel speichern");
        btn_exit = new Button("Spiel beenden");
        grid_stop.add(btn_save, 0, 0);
        grid_stop.add(btn_exit, 1, 0);
        grid_main.add(label_title, 0, 0);
        grid_main.add(grid_powerups, 0, 1);
        grid_main.add(grid_center, 1, 1);
        grid_main.add(grid_tools, 2, 1);
        grid_main.add(grid_stop, 1, 2);
        grid_main.add(scroll_tools, 2, 1);
        grid_main.add(scroll_powerups, 0, 1);
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
        btn_save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Save.saveTools(toolsList, ID_User);
                Save.saveUpgrades(upgradesList, ID_User);
                Save.saveBalance(game.getBalance(), ID_User);
            }
        });


        scene1 = new Scene(grid_main, 1500, 775);
        scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        AnimationTimer timer =  new AnimationTimer(){

            @Override
            public void handle(long now) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                updateLabels();
            }
        };
        timer.start();
        return scene1;
    }

    public void updateLabels() {
        if (toolsList.size() >= 1) {
            game.addMoney(toolsList);
        }
        if (label_balance != null) {
            int balance = (int) game.getBalance();
            label_balance.setText(balance + " $");
            int moneyPerSecond = (int) game.calcMoneyPerSecond(toolsList);
            label_moneyPerSecond.setText(moneyPerSecond + "$ pro Sekunde");
        }
    }

    public String priceGeneratorLong(double number) {
        String result = "";
        if (number > 1000000 && number < 10000000) {
            result = Double.toString(number);
            char[] array_number = new char[result.length()];
            for (int i = 0; i < result.length(); i++) {
                array_number[i] = result.charAt(i);
            }
            result = array_number[0]+"."+array_number[1]+array_number[2]+array_number[3]+" Millionen CHF";
        }
        else if (number > 1000 && number < 10000) {
            result = Double.toString(number);
            char[] array_number = new char[result.length()];
            for (int i = 0; i < result.length(); i++) {
                array_number[i] = result.charAt(i);
            }
            result = array_number[0]+"'"+array_number[1]+array_number[2]+array_number[3]+ " CHF";
        }
        else if (number > 10000 && number < 100000) {
            result = Double.toString(number);
            char[] array_number = new char[result.length()];
            for (int i = 0; i < result.length(); i++) {
                array_number[i] = result.charAt(i);
            }
            result = array_number[0]+array_number[1]+"'"+array_number[2]+array_number[3]+array_number[4]+ " CHF";
        }
        else if (number > 100000 && number < 1000000) {
            result = Double.toString(number);
            char[] array_number = new char[result.length()];
            for (int i = 0; i < result.length(); i++) {
                array_number[i] = result.charAt(i);
            }
            result = array_number[0]+array_number[1]+array_number[2]+array_number[3]+array_number[4]+array_number[5]+ " CHF";
        }
        else if (number > 10000000 && number < 100000000) {
            result = Double.toString(number);
            char[] array_number = new char[result.length()];
            for (int i = 0; i < result.length(); i++) {
                array_number[i] = result.charAt(i);
            }
            result = array_number[0]+array_number[1]+"."+array_number[2]+array_number[3]+" Millionen CHF";
        }
        else if (number > 100000000 && number < 1000000000) {
            System.out.println(number);
            result = Double.toString(number);
            System.out.println(result);
            char[] array_number = new char[result.length()];
            for (int i = 0; i < result.length(); i++) {
                array_number[i] = result.charAt(i);
            }
            System.out.println(array_number);
            result = array_number[0]+array_number[1]+array_number[2]+"."+array_number[3]+" Millionen CHF";
        }
        else if (number > 1000000000 && number < 2147483647) {
            result = Double.toString(number);
            char[] array_number = new char[result.length()];
            for (int i = 0; i < result.length(); i++) {
                array_number[i] = result.charAt(i);
            }
            result = array_number[0]+"."+array_number[1]+array_number[2]+array_number[3]+" Milliarden CHF";
        }
        else{
            result = Double.toString(number)+" CHF";
        }

        return (result);

    }
}
