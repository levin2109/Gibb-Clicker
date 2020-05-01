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
import sun.misc.ClassLoaderUtil;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class GUI extends Application {
    private static List<Tools> toolsList = new ArrayList<>();
    private List<Button> powerupsList = new ArrayList<>();
    private static List<Upgrades> upgradesList = new ArrayList<>();
    private List<Button> toolsButton = new ArrayList<>();
    private static Gibb game = new Gibb(0);
    private int idUser;
    private String username;

    /*-----------------------------------------------------
                 GUI Bauteile & Deklarationen
    ------------------------------------------------------*/
    Scene login, scene1, registartion;
    Label labelPowerups1, labelBalance, labelMoneyPerSecond, labelTitle, labelTools, labelLoginUsername, labelLoginPassword, labelLoginTitle, labelLoginVerification,
            labelRegistrationUsername, labelRegistrationPassword1, labelRegistrationPassword2, labelRegistrationTitle, labelRegistrationVerification;
    Button btnGibb, btnSave, btnExit,
            btnLogin, btnLoginRegistration, btnRegistration, btnRegistrationLogin;
    TextField usernameLogin, usernameRegistration;
    PasswordField passwordLogin, passwordRegistration1, passwordRegistration2;

    /*-----------------------------------
          create Objects from JDBC's
    -----------------------------------*/
    SaveJDBCDoa Save = new SaveJDBCDoa();
    UserJDBCDoa User = new UserJDBCDoa();
    UpgradesJDBCDoa Upgrades = new UpgradesJDBCDoa();
    ToolsJDBCDoa Tools = new ToolsJDBCDoa();

    /*---------------------------------
                  methods
     --------------------------------*/
    //start the GUI
    public void startApplication(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        /* Login */
        GridPane gridLogin = new GridPane();
        labelLoginTitle = new Label("Anmelden");
        labelLoginUsername = new Label("Benutzername: ");
        usernameLogin = new TextField();
        labelLoginPassword = new Label("Passwort: ");
        passwordLogin = new PasswordField();
        labelLoginVerification = new Label();
        btnLogin = new Button("Einloggen");
        btnLoginRegistration = new Button("Noch keinen Account erstellt?");
        gridLogin.add(labelLoginTitle, 0, 0);
        gridLogin.add(labelLoginUsername, 0, 1);
        gridLogin.add(usernameLogin, 1, 1);
        gridLogin.add(labelLoginPassword, 0, 2);
        gridLogin.add(passwordLogin, 1, 2);
        gridLogin.add(labelLoginVerification, 0, 3);
        gridLogin.add(btnLogin, 0, 4);
        gridLogin.add(btnLoginRegistration, 0, 5);
        gridLogin.setColumnSpan(labelLoginTitle, 400);
        gridLogin.setColumnSpan(labelLoginVerification, 400);
        gridLogin.setColumnSpan(btnLoginRegistration, 400);
        gridLogin.setPadding(new Insets(5, 20, 20, 20));
        gridLogin.setVgap(7);
        gridLogin.getStyleClass().add("gridLogin");
        labelLoginUsername.setPadding(new Insets(0, 15, 0, 0));
        labelLoginPassword.setPadding(new Insets(0, 15, 0, 0));
        labelLoginTitle.setFont(Font.font("Helvetica", 20));
        labelLoginVerification.setFont(Font.font("Helvetica", 0));

        //check login and load game
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (event.getSource() == btnLogin) {
                    if (usernameLogin.getText().isEmpty() || usernameLogin.getText() == null || passwordLogin.getText().isEmpty() || passwordLogin.getText() == null) {
                        labelLoginVerification.setText("Der Name und/oder Passwort sind leer");
                        labelLoginVerification.setTextFill(Color.web("red"));
                        labelLoginVerification.setFont(Font.font("Helvetica", 12));
                    } else {
                        if (User.checkPassword(usernameLogin.getText(), passwordLogin.getText())) {
                            toolsList = Tools.loadTools(usernameLogin.getText());
                            upgradesList = Upgrades.loadUpgrades(usernameLogin.getText());
                            for (Tools tool : toolsList) {
                                if (tool.isStatus() == true) {
                                    tool.loadMultiplier(usernameLogin.getText());
                                    tool.updatePrice(tool.getToolID(), usernameLogin.getText());
                                    tool.loadMoneyPerSecond(usernameLogin.getText());
                                }
                            }
                            game.loadMultiplier(upgradesList);
                            username = usernameLogin.getText();
                            idUser = User.getUserID(username);
                            game.loadBalance(username);
                            primaryStage.setScene(scene1());
                            primaryStage.show();
                        } else {
                            labelLoginVerification.setText("Der Name und/oder Passwort sind falsch");
                            labelLoginVerification.setTextFill(Color.web("red"));
                            labelLoginVerification.setFont(Font.font("Helvetica", 12));
                        }
                    }
                }
            }
        });

        //load registration after click on button
        btnLoginRegistration.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (event.getSource() == btnLoginRegistration) {
                    primaryStage.setScene(registartion);
                    primaryStage.show();
                }
            }

        });


        /* Registration */
        GridPane gridRegistration = new GridPane();
        labelRegistrationTitle = new Label("Registrieren");
        labelRegistrationUsername = new Label("Benutzername: ");
        usernameRegistration = new TextField();
        labelRegistrationPassword1 = new Label("Passwort: ");
        labelRegistrationPassword2 = new Label("Passwort wiederholen: ");
        passwordRegistration1 = new PasswordField();
        passwordRegistration2 = new PasswordField();
        labelRegistrationVerification = new Label();
        btnRegistration = new Button("Registrieren");
        btnRegistrationLogin = new Button("Bereits einen Account erstellt?");
        gridRegistration.add(labelRegistrationTitle, 0, 0);
        gridRegistration.add(labelRegistrationUsername, 0, 1);
        gridRegistration.add(usernameRegistration, 1, 1);
        gridRegistration.add(labelRegistrationPassword1, 0, 2);
        gridRegistration.add(passwordRegistration1, 1, 2);
        gridRegistration.add(labelRegistrationPassword2, 0, 3);
        gridRegistration.add(passwordRegistration2, 1, 3);
        gridRegistration.add(labelRegistrationVerification, 0, 4);
        gridRegistration.add(btnRegistration, 0, 5);
        gridRegistration.add(btnRegistrationLogin, 0, 6);
        gridRegistration.setColumnSpan(labelRegistrationTitle, 400);
        gridRegistration.setColumnSpan(labelRegistrationVerification, 400);
        gridRegistration.setColumnSpan(btnRegistrationLogin, 400);
        gridRegistration.setPadding(new Insets(5, 20, 20, 20));
        gridRegistration.setVgap(7);
        gridRegistration.getStyleClass().add("gridRegistration");
        labelRegistrationUsername.setPadding(new Insets(0, 15, 0, 0));
        labelRegistrationPassword1.setPadding(new Insets(0, 15, 0, 0));
        labelRegistrationPassword2.setPadding(new Insets(0, 15, 0, 0));
        labelRegistrationTitle.setFont(Font.font("Helvetica", 20));
        labelRegistrationVerification.setFont(Font.font("Helvetica", 0));

        //check the registration and write in database, load game
        btnRegistration.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (event.getSource() == btnRegistration) {
                    if (usernameRegistration.getText().isEmpty() || usernameRegistration.getText() == null || passwordRegistration1.getText().isEmpty() ||
                            passwordRegistration1.getText() == null || passwordRegistration2.getText().isEmpty() || passwordRegistration2.getText() == null) {
                        labelRegistrationVerification.setText("Der Name und/oder Passwort sind leer");
                        labelRegistrationVerification.setTextFill(Color.web("red"));
                        labelRegistrationVerification.setFont(Font.font("Helvetica", 12));
                    } else if (!passwordRegistration1.getText().equals(passwordRegistration2.getText())) {
                        labelRegistrationVerification.setText("Die Passwörter stimmen nicht überein.");
                        labelRegistrationVerification.setTextFill(Color.web("red"));
                        labelRegistrationVerification.setFont(Font.font("Helvetica", 12));
                    } else if (passwordRegistration1.getText().equals(passwordRegistration2.getText())) {
                        if (User.checkUsername(usernameRegistration.getText())) {
                            User.registrationUser(usernameRegistration.getText(), passwordRegistration1.getText(), 0);
                            int userId = User.getUserID(usernameRegistration.getText());
                            for (int i = 1; i <= 10; i++) {
                                Tools.registrateTools(userId, i, 0, false);
                            }
                            for (int i = 1; i <= 40; i++) {
                                Upgrades.registrateUpgrades(userId, i, false);
                            }
                            toolsList = Tools.loadTools(usernameRegistration.getText());
                            upgradesList = Upgrades.loadUpgrades(usernameRegistration.getText());
                            username = usernameRegistration.getText();
                            idUser = User.getUserID(usernameRegistration.getText());
                            primaryStage.setScene(scene1());
                            primaryStage.show();
                        } else {
                            labelRegistrationVerification.setText("Der Benutzername existiert bereits.");
                            labelRegistrationVerification.setTextFill(Color.web("red"));
                            labelRegistrationVerification.setFont(Font.font("Helvetica", 12));
                        }
                    }
                }
            }
        });

        //load login after click on login button
        btnRegistrationLogin.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (event.getSource() == btnRegistrationLogin) {
                    primaryStage.setScene(login);
                    primaryStage.show();
                }
            }
        });

        //Load login as first scene
        login = new Scene(gridLogin, 300, 230);
        registartion = new Scene(gridRegistration, 340, 250);
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        if (primaryStage != null) {
            primaryStage.setTitle("Gibb Clicker");
            primaryStage.setScene(login);
            primaryStage.show();
        }


    }

    public Scene scene1() {

        /* Grids */
        GridPane gridMain = new GridPane();
        GridPane gridCenter = new GridPane();
        GridPane gridTools = new GridPane();
        GridPane gridPowerups = new GridPane();
        GridPane gridStop = new GridPane();
        ScrollPane scrollTools = new ScrollPane();
        ScrollPane scrollPowerups = new ScrollPane();
        gridMain.setMinWidth(1500);
        gridMain.setMaxWidth(1500);
        gridMain.setMaxHeight(750);
        gridMain.setMinHeight(750);

        /* Power Ups */
        labelPowerups1 = new Label("Powerups");
        int row = 1;
        float iButFloat = 0;
        gridPowerups.add(labelPowerups1, 0, 0);
        for (int i = 0; i < upgradesList.size(); i++) {
            if (iButFloat / 5 % 1 == 0) {
                row++;
            }
            Button powerup = new Button();
            powerup.setId(upgradesList.get(i).getName());
            powerup.setMinWidth(70);
            powerup.setMinHeight(70);
            gridPowerups.setHgap(9);
            gridPowerups.setVgap(9);
            gridPowerups.add(powerup, i % 5, row);
            powerupsList.add(powerup);
            iButFloat = iButFloat + 1;
            String tooltipTextName = "Name: " + upgradesList.get(i).getName();
            String tooltipTextPrice = "Preis: " + priceGeneratorLong(upgradesList.get(i).getPrice());
            String tooltipTextMultiplier = "Dein/e " + Tools.getToolName(upgradesList.get(i).getTool_ID()) + " nimmt " + upgradesList.get(i).getMultiplier() + "-mal so viel Geld ein.";
            Tooltip tooltipPowerup = TooltipBuilder.create().text(tooltipTextName + "\n" + tooltipTextPrice + "\n" + tooltipTextMultiplier).prefWidth(300).wrapText(true).build();
            powerup.setTooltip(tooltipPowerup);
            switch (upgradesList.get(i).getTool_ID()) {
                case 1:
                    powerup.getStyleClass().add("powerupSmartlearn");
                    break;
                case 2:
                    powerup.getStyleClass().add("powerupVMs");
                    break;
                case 3:
                    powerup.getStyleClass().add("powerupLehrer");
                    break;
                case 4:
                    powerup.getStyleClass().add("powerupKantine");
                    break;
                case 5:
                    powerup.getStyleClass().add("powerupPcs");
                    break;
                case 6:
                    powerup.getStyleClass().add("powerupHeizung");
                    break;
                case 7:
                    powerup.getStyleClass().add("powerupKlimaanlage");
                    break;
                case 8:
                    powerup.getStyleClass().add("powerupWCs");
                    break;
                case 9:
                    powerup.getStyleClass().add("powerupKlangbruecke");
                    break;
                case 10:
                    powerup.getStyleClass().add("powerupRussischeReviews");
                    break;
                case 11:
                    powerup.getStyleClass().add("powerupClicker");
                    break;
                default:
            }
        }

        //make function for every powerup
        for (Button btn : powerupsList) {
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String buttonPowerup = String.valueOf(btn.getId());
                    for (int i = 0; i < upgradesList.size(); i++) {
                        if (buttonPowerup.equals(upgradesList.get(i).getName())) {
                            upgradesList.get(i).buy(game,toolsList);

                        }
                    }
                }
            });
        }

        //add all GUI elements into grid
        labelPowerups1.setAlignment(Pos.CENTER);
        labelPowerups1.getStyleClass().add("labelPowerups");
        gridPowerups.setColumnSpan(labelPowerups1, 400);
        gridPowerups.setMinWidth(400);
        gridPowerups.setMaxWidth(400);
        scrollPowerups.setContent(gridPowerups);
        scrollPowerups.setPrefWidth(400);
        scrollPowerups.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPowerups.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPowerups.setStyle("-fx-background-color: #FFFFFF;");
        gridPowerups.setStyle("-fx-background-color: #FFFFFF;");
        gridPowerups.setPadding(new Insets(0, 20, 0, 0));


        /* Center */
        labelBalance = new Label(priceGeneratorLong((long) game.getBalance()));
        btnGibb = new Button();
        btnGibb.setPrefHeight(800);
        btnGibb.setPrefWidth(1500);
        btnGibb.getStyleClass().add("btnGibb");

        labelMoneyPerSecond = new Label(priceGeneratorLong( (long) game.calcMoneyPerSecond(toolsList)) + " pro Sekunde");
        labelTitle = new Label("GIBB Clicker");
        gridCenter.add(labelMoneyPerSecond, 0, 1);
        gridCenter.add(btnGibb, 0, 2);
        gridCenter.add(labelBalance, 0, 0);
        labelTitle.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(labelTitle, 0.0);
        AnchorPane.setRightAnchor(labelTitle, 0.0);
        labelTitle.setAlignment(Pos.CENTER);
        labelMoneyPerSecond.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(labelMoneyPerSecond, 0.0);
        AnchorPane.setRightAnchor(labelMoneyPerSecond, 0.0);
        labelMoneyPerSecond.setAlignment(Pos.CENTER);
        labelBalance.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(labelBalance, 0.0);
        AnchorPane.setRightAnchor(labelBalance, 0.0);
        labelBalance.setAlignment(Pos.CENTER);
        labelTitle.setFont(Font.font("Helvetica", 30));
        labelBalance.setFont(Font.font("Helvetica", 60));
        labelMoneyPerSecond.setFont(Font.font("Helvetica", 20));
        labelPowerups1.setFont(Font.font("Helvetica", 27));
        gridCenter.setMinWidth(700);
        gridCenter.setMaxWidth(700);
        gridCenter.setMaxHeight(700);
        gridCenter.setMinHeight(700);

        //function for the GIBB button
        btnGibb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.click();
            }
        });


        /* Tools */
        labelTools = new Label("Tools");
        List<Label> toolsLabelName = new ArrayList<>();
        List<Label> toolsLabelLevel = new ArrayList<>();
        List<Label> toolsLabelPrice = new ArrayList<>();

        //Alle Tools generieren
        for (int i = 0; i < Tools.loadTools(username).size(); i++) {
            Button toolButton = new Button();
            gridTools.add(toolButton, 0, i + 1);
            switch (Tools.loadTools(username).get(i).getToolID()) {
                case 1:
                    toolButton.getStyleClass().add("powerupSmartlearn");
                    break;
                case 2:
                    toolButton.getStyleClass().add("powerupVMs");
                    break;
                case 3:
                    toolButton.getStyleClass().add("powerupLehrer");
                    break;
                case 4:
                    toolButton.getStyleClass().add("powerupKantine");
                    break;
                case 5:
                    toolButton.getStyleClass().add("powerupPcs");
                    break;
                case 6:
                    toolButton.getStyleClass().add("powerupHeizung");
                    break;
                case 7:
                    toolButton.getStyleClass().add("powerupKlimaanlage");
                    break;
                case 8:
                    toolButton.getStyleClass().add("powerupWCs");
                    break;
                case 9:
                    toolButton.getStyleClass().add("powerupKlangbruecke");
                    break;
                case 10:
                    toolButton.getStyleClass().add("powerupRussischeReviews");
                    break;

            }
            toolsButton.add(toolButton);
            toolButton.setPrefWidth(80);
            toolButton.setPrefHeight(80);

            Label toolLabelName = new Label(toolsList.get(i).getName());
            gridTools.add(toolLabelName, 1, i + 1);
            toolLabelName.getStyleClass().add("labelToolName");
            toolsLabelName.add(toolLabelName);
            toolLabelName.setPadding(new Insets(0,0,0,10));

            Label toolLabelLevel = new Label(Integer.toString(toolsList.get(i).getLevel()));
            gridTools.add(toolLabelLevel, 2, i + 1);
            toolLabelLevel.getStyleClass().add("labelLevel");
            toolsLabelLevel.add(toolLabelLevel);

            Label toolLabelPrice = new Label(priceGeneratorLong((long) (toolsList.get(i).getPrice())));
            gridTools.add(toolLabelPrice, 1, i + 1);
            toolLabelPrice.getStyleClass().add("labelToolPrice");
            toolsLabelPrice.add(toolLabelPrice);
            toolLabelPrice.setPadding(new Insets(0,0,15,10));
            toolLabelPrice.setAlignment(Pos.BOTTOM_LEFT);

        }
        //make the function for every tool
        for (Button btn : toolsButton) {
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String c = String.valueOf(btn.getStyleClass());
                    switch (c) {
                        case "button powerupSmartlearn":
                            toolsList.get(0).buy(game);
                            toolsLabelLevel.get(0).setText(String.valueOf(toolsList.get(0).getLevel()));
                            toolsLabelPrice.get(0).setText(priceGeneratorLong((long) (toolsList.get(0).getPrice()*(1.6*toolsList.get(0).getLevel()))));
                            break;
                        case "button powerupVMs":
                            toolsList.get(1).buy(game);
                            toolsLabelLevel.get(1).setText(String.valueOf(toolsList.get(1).getLevel()));
                            toolsLabelPrice.get(1).setText(priceGeneratorLong((long) (toolsList.get(1).getPrice()*(1.6*toolsList.get(1).getLevel()))));
                            break;
                        case "button powerupLehrer":
                            toolsList.get(2).buy(game);
                            toolsLabelLevel.get(2).setText(String.valueOf(toolsList.get(2).getLevel()));
                            toolsLabelPrice.get(2).setText(priceGeneratorLong((long) (toolsList.get(2).getPrice()*(1.6*toolsList.get(2).getLevel()))));
                            break;
                        case "button powerupKantine":
                            toolsList.get(3).buy(game);
                            toolsLabelLevel.get(3).setText(String.valueOf(toolsList.get(3).getLevel()));
                            toolsLabelPrice.get(3).setText(priceGeneratorLong((long) (toolsList.get(3).getPrice()*(1.6*toolsList.get(3).getLevel()))));
                            break;
                        case "button powerupPcs":
                            toolsList.get(4).buy(game);
                            toolsLabelLevel.get(4).setText(String.valueOf(toolsList.get(4).getLevel()));
                            toolsLabelPrice.get(4).setText(priceGeneratorLong((long) (toolsList.get(4).getPrice()*(1.6*toolsList.get(4).getLevel()))));
                            break;
                        case "button powerupHeizung":
                            toolsList.get(5).buy(game);
                            toolsLabelLevel.get(5).setText(String.valueOf(toolsList.get(5).getLevel()));
                            toolsLabelPrice.get(5).setText(priceGeneratorLong((long) (toolsList.get(5).getPrice()*(1.6*toolsList.get(5).getLevel()))));
                            break;
                        case "button powerupKlimaanlage":
                            toolsList.get(6).buy(game);
                            toolsLabelLevel.get(6).setText(String.valueOf(toolsList.get(6).getLevel()));
                            toolsLabelPrice.get(6).setText(priceGeneratorLong((long) (toolsList.get(6).getPrice()*(1.6*toolsList.get(6).getLevel()))));
                            break;
                        case "button powerupWCs":
                            toolsList.get(7).buy(game);
                            toolsLabelLevel.get(7).setText(String.valueOf(toolsList.get(7).getLevel()));
                            toolsLabelPrice.get(7).setText(priceGeneratorLong((long) (toolsList.get(7).getPrice()*(1.6*toolsList.get(7).getLevel()))));
                            break;
                        case "button powerupKlangbruecke":
                            toolsList.get(8).buy(game);
                            toolsLabelLevel.get(8).setText(String.valueOf(toolsList.get(8).getLevel()));
                            toolsLabelPrice.get(8).setText(priceGeneratorLong((long) (toolsList.get(8).getPrice()*(1.6*toolsList.get(8).getLevel()))));
                            break;
                        case "button powerupRussischeReviews":
                            toolsList.get(9).buy(game);
                            toolsLabelLevel.get(9).setText(String.valueOf(toolsList.get(9).getLevel()));
                            toolsLabelPrice.get(9).setText(priceGeneratorLong((long) (toolsList.get(9).getPrice()*(1.6*toolsList.get(9).getLevel()))));
                            break;
                        default:
                    }
                }
            });
        }

        //Add all GUI elements(Tools) to grid
        gridTools.add(labelTools, 0, 0);
        labelTools.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(labelTools, 0.0);
        AnchorPane.setRightAnchor(labelTools, 0.0);
        labelTools.setAlignment(Pos.CENTER);
        gridMain.setColumnSpan(labelTools, 400);
        labelTools.setFont(Font.font("Helvetica", 27));
        gridTools.setMinWidth(400);
        gridTools.setMaxWidth(400);
        scrollTools.setContent(gridTools);
        scrollTools.setPrefWidth(400);
        scrollTools.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollTools.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollTools.setStyle("-fx-background-color: #FFFFFF;");
        gridTools.setStyle("-fx-background-color: #FFFFFF;");



        /* Main Grid */
        gridMain.getStyleClass().add("gridMain");
        btnSave = new Button("Spiel speichern");
        btnExit = new Button("Spiel beenden");
        gridStop.add(btnSave, 0, 0);
        gridStop.add(btnExit, 1, 0);
        gridStop.setPadding(new Insets(0,0,10,0));
        btnSave.getStyleClass().add("controlButtons");
        btnExit.getStyleClass().add("controlButtons");
        gridMain.add(labelTitle, 0, 0);
        gridMain.add(gridPowerups, 0, 1);
        gridMain.add(gridCenter, 1, 1);
        gridMain.add(gridTools, 2, 1);
        gridMain.add(gridStop, 1, 2);
        gridMain.add(scrollTools, 2, 1);
        gridMain.add(scrollPowerups, 0, 1);
        gridStop.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(gridStop, 0.0);
        AnchorPane.setRightAnchor(gridStop, 0.0);
        gridStop.setAlignment(Pos.CENTER);
        gridStop.setHgap(10);
        gridMain.setColumnSpan(labelTitle, 1500);

        //make exit function
        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        //make save function
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Save.saveTools(toolsList, idUser);
                Save.saveUpgrades(upgradesList, idUser);
                Save.saveBalance(game.getBalance(), idUser);
            }
        });

        //styling from scene1
        scene1 = new Scene(gridMain, 1500, 790);
        scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        //Thread (update labels)
        final long[] sleep = {0};
        AnimationTimer timer =  new AnimationTimer(){
            @Override
            public void handle(long now) {
                sleep[0]++;
                if (sleep[0] % 22 == 0) {
                    updateLabels();
                    checkPowerup();
                    checkTools();
                }
            }
        };
        timer.start();
        return scene1;
    }

    //check the powerups and give it a new look
    public void checkPowerup() {
        for (int i = 0; i < upgradesList.size(); i++) {
            if (upgradesList.get(i).isStatus()) {
                powerupsList.get(i).setStyle("-fx-border-color: green");
            } else if (upgradesList.get(i).getPrice() <= game.getBalance()) {
                powerupsList.get(i).setStyle("-fx-opacity: 1");

            } else {
                powerupsList.get(i).setStyle("-fx-opacity: 0.5");
            }
        }
    }

    //check the tools and give it new look
    public void checkTools(){
        for(int i = 0; i < toolsList.size(); i++) {
                if (toolsList.get(i).getPrice() * (1.6 * toolsList.get(i).getLevel()) == 0.0) {
                    if(toolsList.get(i).getPrice() <= game.getBalance()){
                        toolsButton.get(i).setStyle("-fx-opacity: 1");
                        toolsButton.get(i).setStyle("-fx-border-color: green");
                    }
                    else{
                        toolsButton.get(i).setStyle("-fx-opacity: 0.5");
                    }
                }
                else if(toolsList.get(i).getPrice() * (1.6 * toolsList.get(i).getLevel()) < game.getBalance()){
                    toolsButton.get(i).setStyle("-fx-opacity: 1");
                    toolsButton.get(i).setStyle("-fx-border-color: green");
            }
                else if (toolsList.get(i).getLevel() >= 1) {
                    toolsButton.get(i).setStyle("-fx-opacity: 1");
                    toolsButton.get(i).setStyle("-fx-border-color: black");
                } else {
                    toolsButton.get(i).setStyle("-fx-opacity: 0.5");
                }
            }
        }


    //update labels (moneyPerSecond, balance)
    public void updateLabels() {
        if (toolsList.size() >= 1) {
            game.addMoney(toolsList);
        }
        if (labelBalance != null) {
            String balance = priceGeneratorLong((long) game.getBalance());
            labelBalance.setText(balance);
            labelMoneyPerSecond.setText(priceGeneratorLong((long) game.calcMoneyPerSecond(toolsList))  + " pro Sekunde");
        }
    }

    //make balance label number with ' example: 1'000
    public String priceGeneratorLong(long number) {
        String result = "";
        if (number >= 1000000 && number < 10000000) {
            result = Long.toString(number);
            char[] arrayNumber = new char[result.length()];
            for (int i = 0; i < result.length(); i++) {
                arrayNumber[i] = result.charAt(i);
            }
            result = arrayNumber[0] + "." + arrayNumber[1] + arrayNumber[2] + " Millionen CHF";
        } else if (number >= 1000 && number < 10000) {
            result = Long.toString(number);
            char[] arrayNumber = new char[result.length()];
            for (int i = 0; i < result.length(); i++) {
                arrayNumber[i] = result.charAt(i);
            }
            result = arrayNumber[0] + "'" + arrayNumber[1] + arrayNumber[2] + arrayNumber[3] + " CHF";
        } else if (number >= 10000 && number < 100000) {
            result = Long.toString(number);
            char[] arrayNumber = new char[result.length()];
            for (int i = 0; i < result.length(); i++) {
                arrayNumber[i] = result.charAt(i);
            }
            result = arrayNumber[0] +""+ arrayNumber[1] + "'" + arrayNumber[2] + arrayNumber[3] + arrayNumber[4] + " CHF";
        } else if (number >= 100000 && number < 1000000) {
            result = Long.toString(number);
            char[] arrayNumber = new char[result.length()];
            for (int i = 0; i < result.length(); i++) {
                arrayNumber[i] = result.charAt(i);
            }
            result = arrayNumber[0] +""+ arrayNumber[1] + arrayNumber[2] + "'" + arrayNumber[3] + arrayNumber[4] + arrayNumber[5] + " CHF";
        } else if (number >= 10000000 && number < 100000000) {
            result = Long.toString(number);
            char[] arrayNumber = new char[result.length()];
            for (int i = 0; i < result.length(); i++) {
                arrayNumber[i] = result.charAt(i);
            }
            result = arrayNumber[0] +""+ arrayNumber[1] + "." + arrayNumber[2] + " Millionen CHF";
        } else if (number >= 100000000 && number < 1000000000) {
            result = Long.toString(number);
            char[] arrayNumber = new char[result.length()];
            for (int i = 0; i < result.length(); i++) {
                arrayNumber[i] = result.charAt(i);
            }
            result = arrayNumber[0] +""+ arrayNumber[1] + arrayNumber[2] + "." + arrayNumber[3] + " Millionen CHF";
        } else if (number >= 100 && number < 1000) {
            result = Long.toString(number);
            char[] arrayNumber = new char[result.length()];
            for (int i = 0; i < result.length(); i++) {
                arrayNumber[i] = result.charAt(i);
            }
            result = arrayNumber[0]+"" + arrayNumber[1] + arrayNumber[2] + " CHF";
        } else if (number >= 10 && number < 100) {
            result = Long.toString(number);
            char[] arrayNumber = new char[result.length()];
            for (int i = 0; i < result.length(); i++) {
                arrayNumber[i] = result.charAt(i);
            }
            result = arrayNumber[0]+"" + arrayNumber[1] + " CHF";
        } else if (number >= 0 && number < 10) {
            result = Long.toString(number);
            char[] arrayNumber = new char[result.length()];
            for (int i = 0; i < result.length(); i++) {
                arrayNumber[i] = result.charAt(i);
            }
            result = arrayNumber[0]+""+ " CHF";
        }
        return (result);
    }

    /*--------------------------------------
                    Getter
    --------------------------------------*/

    public String getUsername() {
        return username;
    }

    public int getIdUser() {
        return idUser;
    }

    public static Gibb getGame() {
        return game;
    }

    public static List<sample.Upgrades> getUpgradesList() {
        return upgradesList;
    }

    public static List<sample.Tools> getToolsList() {
        return toolsList;
    }
    /*--------------------------------------
                    Setter
    --------------------------------------*/

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public static void setGame(Gibb game) {
        GUI.game = game;
    }

    public static void setUpgradesList(List<sample.Upgrades> upgradesList) {
        GUI.upgradesList = upgradesList;
    }

    public static void setToolsList(List<sample.Tools> toolsList) {
        GUI.toolsList = toolsList;
    }
}

