package com.example.improvisedzen;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;

public class Authentication extends Stage {

    private static String usersPath = "src/main/files/users.txt";
    private static  String detailsPath = "src/main/files/usersDetails.txt";
    private Image icon;
    private BorderPane root;
    private AnchorPane top;
    private AnchorPane bottom;
    private Rectangle background;
    private Rectangle shell;
    private Text name;
    private Text tagline;
    private DropShadow nameFX;
    private Label page;
    private Label status;
    private TextField usernameField;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private ToggleButton button;
    private DropShadow buttonFX;
    private Label hint;
    private Hyperlink link;
    private FadeTransition animatePage;
    private FadeTransition animateUF;
    private FadeTransition animatePF;
    private FadeTransition animateCPF;
    private FadeTransition animateButton;
    private Scene scene;

    public void LoginPage(Stage stage) {

        icon = new Image(getClass().getResourceAsStream("icon.jpg"));

        root = new BorderPane();
        root.setPrefSize(427, 613);
        root.setStyle("-fx-border-radius: 12; -fx-background-radius: 12;");

        // Top
        top = new AnchorPane();
        top.setPrefSize(427, 131);

        background = new Rectangle(427, 131, Color.web("#00928f"));
        background.setArcHeight(5.0);
        background.setArcWidth(5.0);
        background.setLayoutY(0.0);

        name = new Text("ZEN.");
        name.setFill(Color.WHITE);
        name.setLayoutX(165.0);
        name.setLayoutY(74.0);
        name.setFont(Font.font("Tahoma", FontWeight.BOLD, 42.0));

        tagline = new Text("The start to a more conscious spending.");
        tagline.setFill(Color.WHITE);
        tagline.setLayoutX(79.0);
        tagline.setLayoutY(96.0);
        tagline.setWrappingWidth(263.1903381347656);
        tagline.setFont(Font.font("Ubuntu Sans", 14.0));

        nameFX = new DropShadow();
        nameFX.setOffsetY(4.67);
        nameFX.setColor(new Color(0, 0, 0, 0.6781609058380127));
        name.setEffect(nameFX);

        top.getChildren().addAll(background, tagline, name);
        root.setTop(top);

        // Bottom
        bottom = new AnchorPane();
        bottom.setPrefSize(427, 482);

        shell = new Rectangle(427, 482, Color.WHITE);
        shell.setArcHeight(20.0);
        shell.setArcWidth(20.0);
        shell.setStyle("-fx-border-radius: 20; -fx-background-radius: 20;");

        page = new Label("LOG IN");
        page.setLayoutX(157.0);
        page.setLayoutY(31.0);
        page.setTextFill(Color.web("#00000070"));
        page.setFont(Font.font("Inter Medium", 29.0));

        status = new Label();
        status.setLayoutX(94.0);
        status.setLayoutY(104.0);
        status.setPrefSize(319, 15);
        status.setFont(Font.font(16.0));

        passwordField = new PasswordField();
        passwordField.setLayoutX(34.0);
        passwordField.setLayoutY(241.0);
        passwordField.setPrefSize(360, 52);
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-background-color: #e3e4e4; -fx-background-radius: 12; -fx-border-color: WHITE;");
        passwordField.setFont(Font.font("Inter Medium", 15.0));
        passwordField.setPadding(new Insets(0, 0, 0, 31));

        usernameField = new TextField();
        usernameField.setLayoutX(34.0);
        usernameField.setLayoutY(166.0);
        usernameField.setPrefSize(360, 52);
        usernameField.setPromptText("Username");
        usernameField.setStyle("-fx-background-color: #e3e4e4; -fx-background-radius: 12; -fx-border-color: WHITE;");
        usernameField.setFont(Font.font("Inter Medium", 15.0));
        usernameField.setPadding(new Insets(0, 0, 0, 31));

        button = new ToggleButton("LOG IN");
        button.setLayoutX(34.0);
        button.setLayoutY(397.0);
        button.setPrefSize(360, 39);
        button.getStyleClass().add("submitButton-style");

        buttonFX = new DropShadow(43.1725, Color.web("#0000003d"));
        buttonFX.setHeight(50.16);
        buttonFX.setWidth(124.53);
        buttonFX.setOffsetY(10.0);
        button.setEffect(buttonFX);
        button.setOnAction(e -> {

            if (usernameField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {

                if(ValidateLogin(usernameField, passwordField) == true) {

                    status.setText("Welcome, " + usernameField.getText().toUpperCase() + "!");
                    status.setStyle("-fx-text-fill: black");
                    status.setLayoutX(50.0);
                    status.setAlignment(Pos.CENTER);
                    navigateToDashboard(stage,usernameField.getText());
                }
                else {

                    status.setText("Wrong username/password");
                    status.setStyle("-fx-text-fill: red");
                }
            }
            else {
                status.setText("Fill in username and password.");
                status.setStyle("-fx-text-fill: red");
            }
        });

        hint = new Label("Ready to get started? Click");
        hint.setFont(Font.font("Ubuntu Sans"));
        hint.setLayoutX(117.0);
        hint.setLayoutY(446.0);

        link = new Hyperlink("here");
        link.setId("SwitchButton");
        link.setLayoutX(261.0);
        link.setLayoutY(443.0);
        link.setFont(Font.font("Ubuntu Sans"));
        link.setTextFill(Color.web("#d59f0f"));

        animateUF = new FadeTransition(Duration.millis(1000));
        animatePF = new FadeTransition(Duration.millis(1000));
        animateButton = new FadeTransition(Duration.millis(1000));
        animatePage = new FadeTransition(Duration.millis(1000));

        // Set the target nodes for the animation
        animateUF.setNode(usernameField);
        animatePF.setNode(passwordField);
        animatePF.setNode(button);
        animatePage.setNode(page);

        // Set the initial and final opacity values for the animation
        animateUF.setFromValue(0);
        animateUF.setToValue(1);
        animateUF.setCycleCount(0);

        animatePF.setFromValue(0);
        animatePF.setToValue(1);
        animatePF.setCycleCount(0);

        animatePF.setFromValue(0);
        animatePF.setToValue(1);
        animatePF.setCycleCount(0);

        animatePage.setFromValue(0);
        animatePage.setToValue(1);
        animatePage.setCycleCount(0);

        // Start the animation
        animateUF.play();
        animatePF.play();
        animatePF.play();
        animatePage.play();

        bottom.getChildren().addAll(shell,  page, status, passwordField, usernameField, button, hint, link);
        root.setBottom(bottom);
        
        scene = new Scene(root, 427, 613);
        scene.getStylesheets().add(getClass().getResource("Styling.css").toExternalForm());
        
        link.setOnAction(e -> {
            SignupPage(stage);
        });

        stage.setScene(scene);
        stage.getIcons().add(icon);
        stage.centerOnScreen();
        stage.setTitle("Zen.");
        stage.setResizable(false);
        stage.show();
    }
    
    public void SignupPage(Stage stage) {

        root = new BorderPane();
        root.setPrefSize(427, 613);
        root.setStyle("-fx-border-radius: 12; -fx-background-radius: 12;");

        //Top
        top = new AnchorPane();
        top.setPrefSize(427, 131);

        background = new Rectangle(427, 131, Color.web("#00928f"));
        background.setArcHeight(5.0);
        background.setArcWidth(5.0);
        background.setLayoutY(0.0);

        name = new Text("ZEN.");
        name.setFill(Color.WHITE);
        name.setLayoutX(165.0);
        name.setLayoutY(74.0);
        name.setFont(Font.font("Tahoma", FontWeight.BOLD, 42.0));
        nameFX = new DropShadow();
        nameFX.setOffsetY(4.67);
        nameFX.setColor(new Color(0, 0, 0, 0.6781609058380127));
        name.setEffect(nameFX);

        tagline = new Text("The start to a more conscious spending.");
        tagline.setFill(Color.WHITE);
        tagline.setLayoutX(79.0);
        tagline.setLayoutY(96.0);
        tagline.setWrappingWidth(263.1903381347656);
        tagline.setFont(Font.font("Ubuntu Sans", 14.0));

        top.getChildren().addAll(background, tagline, name);
        root.setTop(top);

        //Bottom
        bottom = new AnchorPane();
        bottom.setPrefSize(427, 482);

        shell = new Rectangle(427, 482, Color.WHITE);
        shell.setArcHeight(20.0);
        shell.setArcWidth(20.0);
        shell.setStyle("-fx-border-radius: 20; -fx-background-radius: 20;");

        page = new Label("SIGN IN");
        page.setLayoutX(157.0);
        page.setLayoutY(31.0);
        page.setTextFill(Color.web("#00000070"));
        page.setFont(Font.font("Inter Medium", 29.0));

        status = new Label();
        status.setLayoutX(94.0);
        status.setLayoutY(104.0);
        status.setPrefSize(319, 15);
        status.setFont(Font.font(16.0));

        passwordField = new PasswordField();
        passwordField.setLayoutX(34.0);
        passwordField.setLayoutY(241.0);
        passwordField.setPrefSize(360, 52);
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-background-color: #e3e4e4; -fx-background-radius: 12; -fx-border-color: WHITE;");
        passwordField.setFont(Font.font("Inter Medium", 15.0));
        passwordField.setPadding(new Insets(0, 0, 0, 31));

        confirmPasswordField = new PasswordField();
        confirmPasswordField.setLayoutX(34.0);
        confirmPasswordField.setLayoutY(316.0);
        confirmPasswordField.setPrefSize(360, 52);
        confirmPasswordField.setPromptText("Confirm Password");
        confirmPasswordField.setStyle("-fx-background-color: #e3e4e4; -fx-background-radius: 12; -fx-border-color: WHITE;");
        confirmPasswordField.setFont(Font.font("Inter Medium", 15.0));
        confirmPasswordField.setPadding(new Insets(0, 0, 0, 31));

        usernameField = new TextField();
        usernameField.setLayoutX(34.0);
        usernameField.setLayoutY(166.0);
        usernameField.setPrefSize(360, 52);
        usernameField.setPromptText("Username");
        usernameField.setStyle("-fx-background-color: #e3e4e4; -fx-background-radius: 12; -fx-border-color: WHITE;");
        usernameField.setFont(Font.font("Inter Medium", 15.0));
        usernameField.setPadding(new Insets(0, 0, 0, 31));

        button = new ToggleButton("SIGN IN");
        button.setLayoutX(34.0);
        button.setLayoutY(397.0);
        button.setPrefSize(360, 39);
        button.getStyleClass().add("submitButton-style");
        buttonFX = new DropShadow(43.1725, Color.web("#0000003d"));
        buttonFX.setHeight(50.16);
        buttonFX.setWidth(124.53);
        buttonFX.setOffsetY(10.0);
        button.setEffect(buttonFX);
        button.setOnAction(e -> {

            if (usernameField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {

                if (passwordField.getText().equals(confirmPasswordField.getText())) {

                    SignupUser(usernameField, confirmPasswordField, status);
                }
                else {
                    status.setLayoutX(124);
                    status.setText("Password do not match");
                    status.setStyle("-fx-text-fill: red");
                }
            }
            else {

                status.setText("Fill in username and password.");
                status.setStyle("-fx-text-fill: red");
            }
        });

        hint = new Label("Already has an account? Click");
        hint.setFont(Font.font("Ubuntu Sans"));
        hint.setLayoutX(111.0);
        hint.setLayoutY(446.0);

        link = new Hyperlink("here");
        link.setLayoutX(266.0);
        link.setLayoutY(443.0);
        link.setFont(Font.font("Ubuntu Sans"));
        link.setTextFill(Color.web("#d59f0f"));

        animateUF = new FadeTransition(Duration.millis(1000));
        animatePF = new FadeTransition(Duration.millis(1000));
        animateButton = new FadeTransition(Duration.millis(1000));
        animatePage = new FadeTransition(Duration.millis(1000));
        animateCPF = new FadeTransition(Duration.millis(1000));

        // Set the target nodes for the animation
        animateUF.setNode(usernameField);
        animatePF.setNode(passwordField);
        animateCPF.setNode(confirmPasswordField);
        animateButton.setNode(button);
        animatePage.setNode(page);

        // Set the initial and final opacity values for the animation
        animateUF.setFromValue(0);
        animateUF.setToValue(1);
        animateUF.setCycleCount(0);

        animatePF.setFromValue(0);
        animatePF.setToValue(1);
        animatePF.setCycleCount(0);

        animateButton.setFromValue(0);
        animateButton.setToValue(1);
        animateButton.setCycleCount(0);

        animatePage.setFromValue(0);
        animatePage.setToValue(1);
        animatePage.setCycleCount(0);

        animateCPF.setFromValue(0);
        animateCPF.setToValue(1);
        animateCPF.setCycleCount(0);

        // Start the animation
        animateUF.play();
        animatePF.play();
        animateButton.play();
        animatePage.play();
        animateCPF.play();

        bottom.getChildren().addAll(shell, page, status, passwordField, confirmPasswordField, usernameField, button, hint, link);
        root.setBottom(bottom);

        scene = new Scene(root, 427, 613);
        scene.getStylesheets().add(getClass().getResource("Styling.css").toExternalForm());

        link.setOnAction(e -> {
            LoginPage(stage);
        });

        stage.setScene(scene);
        stage.getIcons().add(icon);
        stage.setTitle("Zen.");
        stage.setResizable(false);
        stage.show();
    }

    public void navigateToDashboard(Stage stage, String username) {

        Dashboard start = new Dashboard();
        start.DashboardPage(stage, username);
    }

    private static boolean ValidateLogin(TextField usernameField, PasswordField passwordField) {

        boolean result = false;
        String[] parts;
        File users = new File(usersPath);

        try (
                FileReader fileReader = new FileReader(users);
                BufferedReader reader = new BufferedReader(fileReader);
        ){

            String line;
            // Check for matching username and password
            while ((line = reader.readLine()) != null) {
                parts = line.split(",");

                if (usernameField.getText().equals(parts[0]) && passwordField.getText().equals(parts[1])) {
                    result = true;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private static void SignupUser(TextField usernameField, PasswordField passwordField, Label label) {

        String[] parts, parts1, parts2;
        String line, line1, line2;

        File users = new File(usersPath);
        File details = new File(detailsPath);

        try (
                BufferedReader reader = new BufferedReader(new FileReader(users));
                BufferedWriter writeToUsers = new BufferedWriter(new FileWriter(users, true));
                BufferedWriter writeToDetails = new BufferedWriter(new FileWriter(details, true));
        ){

            //Read user log file
            while ((line = reader.readLine()) != null) {
                parts = line.split(",");

                // Check for unique username
                if (parts[0].equalsIgnoreCase(usernameField.getText())) {
                    showErrorMessage();
                    return;
                }
            }

            // Add new data to the temporary users file
            writeToUsers.write(usernameField.getText() + "," +passwordField.getText() );
            writeToUsers.newLine();

            writeToDetails.write(usernameField.getText() + "," + "0,0,0,0,0");
            writeToDetails.newLine();

            label.setText("Sign in successful! You can log in now");
            label.setLayoutX(64);
            label.setStyle("-fx-text-fill: black");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void showErrorMessage() {

        Alert message = new Alert(Alert.AlertType.NONE);
        message.setAlertType(Alert.AlertType.ERROR);
        message.setTitle("Username is taken");
        message.setContentText("Please enter another username");
        message.showAndWait();
    }
}
