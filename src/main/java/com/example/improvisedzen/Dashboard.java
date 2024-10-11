package com.example.improvisedzen;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dashboard {

    private String username;
    private ArrayList<String> budgetData = new ArrayList<>();

    public Dashboard() {
    }

    public Dashboard(String username) {
        this.username = username;
    }

    public Dashboard(String username, ArrayList<String> budgetData) {
        this.username = username;
        this.budgetData = budgetData;
    }

    public void DashboardPage(Stage stage, String username) {
        setUsername(username);

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #008a87;");

        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);

        HBox root1 = new HBox();
        root1.setSpacing(20);
        root1.setPadding(new Insets(10));
        root1.setAlignment(Pos.CENTER);

        Label welcomeLabel = new Label("Dashboard");
        welcomeLabel.setAlignment(Pos.CENTER);
        welcomeLabel.setFont(Font.font("Inter", FontWeight.BOLD, 60));
        welcomeLabel.setTextFill(Color.WHITE);

        Label greetingLabel = new Label("Welcome " + getUsername().toUpperCase() + "!");
        greetingLabel.setAlignment(Pos.CENTER);
        greetingLabel.setFont(Font.font("Inter", FontWeight.BOLD, 30));
        greetingLabel.setTextFill(Color.WHITE);

        Label quoteLabel = new Label();
        quoteLabel.setFont(Font.font("Inter", FontWeight.BOLD, 30));
        quoteLabel.setTextFill(Color.WHITE);
        animateText(quoteLabel, "\"And do not spend wastefully\"(Quran 17:26).");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label incomeLabel = new Label();
        Label expensesLabel = new Label();
        Label remainingBudgetLabel = new Label();
        Label remainingBudgetLabel1 = new Label();
        Label remainingBudgetLabel2 = new Label();

        VBox incomeBox = createColoredBox(incomeLabel);
        VBox expensesBox = createColoredBox(expensesLabel);
        VBox remainingBudgetBox = createColoredBox(remainingBudgetLabel);
        VBox remainingBudgetBox1 = createColoredBox(remainingBudgetLabel1);
        VBox remainingBudgetBox2 = createColoredBox(remainingBudgetLabel2);

        File userDetails = new File("src/main/files/usersDetails.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(userDetails))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (getUsername().equals(parts[0])) {
                    ArrayList<String> data = new ArrayList<>();
                    for (int i = 1; i < parts.length; i++) {
                        data.add(parts[i]);
                    }
                    setBudgetData(data);
                }
            }

            ArrayList<Double> budgetValues = new ArrayList<>();
            for (String value : getBudgetData()) {
                budgetValues.add(Double.parseDouble(value));
            }

            incomeLabel.setStyle("-fx-font-family: 'Century Gothic'; -fx-font-weight: bold; -fx-font-size: 35; -fx-text-fill: white;");
            incomeLabel.setText("▶ Total income\t\t\t\t\t\t: RM " + budgetValues.get(0));

            expensesLabel.setStyle("-fx-font-family: 'Century Gothic'; -fx-font-weight: bold; -fx-font-size: 35; -fx-text-fill: white;");
            expensesLabel.setText("▶ Total expenses\t\t\t\t\t\t: RM " + budgetValues.get(1));

            remainingBudgetLabel.setStyle("-fx-font-family: 'Century Gothic'; -fx-font-weight: bold; -fx-font-size: 35; -fx-text-fill: white;");
            remainingBudgetLabel.setText("▶ Remaining Budget for Groceries\t\t: RM " + budgetValues.get(2));

            remainingBudgetLabel1.setStyle("-fx-font-family: 'Century Gothic'; -fx-font-weight: bold; -fx-font-size: 35; -fx-text-fill: white;");
            remainingBudgetLabel1.setText("▶ Remaining Budget for Utilities\t\t\t: RM " + budgetValues.get(3));

            remainingBudgetLabel2.setStyle("-fx-font-family: 'Century Gothic'; -fx-font-weight: bold; -fx-font-size: 35; -fx-text-fill: white;");
            remainingBudgetLabel2.setText("▶ Remaining Budget for Entertainment\t: RM " + budgetValues.get(4));

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "File Error", "An error occurred while reading the file.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Data Error", "An error occurred while parsing the numbers.");
            e.printStackTrace();
        }

        gridPane.add(incomeBox, 0, 0);
        gridPane.add(expensesBox, 0, 1);
        gridPane.add(remainingBudgetBox, 0, 2);
        gridPane.add(remainingBudgetBox1, 0, 3);
        gridPane.add(remainingBudgetBox2, 0, 4);

        root.getChildren().addAll(welcomeLabel, greetingLabel, gridPane);
        borderPane.setTop(root);

        VBox bottomVBox = new VBox();
        bottomVBox.setAlignment(Pos.CENTER);
        bottomVBox.setSpacing(10);
        bottomVBox.setPadding(new Insets(10));

        bottomVBox.getChildren().add(quoteLabel); // Add the animated quote label

        Button budgetPlanningButton = new Button("BUDGET PLANNING");
        budgetPlanningButton.setStyle("-fx-background-color: #d59f0f; -fx-text-fill: white;");
        budgetPlanningButton.setFont(Font.font("Inter", FontWeight.BOLD, 17));

        Button transactionManagementButton = new Button("TRANSACTION MANAGEMENT");
        transactionManagementButton.setStyle("-fx-background-color: #d59f0f; -fx-text-fill: white;");
        transactionManagementButton.setFont(Font.font("Inter", FontWeight.BOLD, 17));

        Button logOut = new Button("LOG OUT");
        logOut.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");
        logOut.setFont(Font.font("Inter", FontWeight.BOLD, 17));

        budgetPlanningButton.setOnAction(e -> navigateToBudget(stage, getUsername()));
        transactionManagementButton.setOnAction(e -> navigateToTransaction(stage, getUsername()));
        logOut.setOnAction(e -> navigateToLogin(stage));

        root1.getChildren().addAll(budgetPlanningButton, transactionManagementButton, logOut);
        bottomVBox.getChildren().add(root1);
        borderPane.setBottom(bottomVBox);
        BorderPane.setAlignment(root1, Pos.CENTER);

        Scene scene = new Scene(borderPane, 1100, 760);
        stage.setScene(scene);
        stage.setTitle("Zen.");
        stage.centerOnScreen();
        stage.show();
    }

    private VBox createColoredBox(Label label) {
        VBox box = new VBox(label);
        box.setStyle("-fx-background-color: #d59f0f; -fx-padding: 10; -fx-border-color: white; -fx-border-width: 2; -fx-background-radius: 15; -fx-border-radius: 15;");
        box.setAlignment(Pos.CENTER_LEFT);
        return box;
    }

    private void animateText(Label label, String text) {
        Timeline timeline = new Timeline();
        Duration timePoint = Duration.ZERO;
        Duration pause = Duration.millis(50);
        for (int i = 0; i < text.length(); i++) {
            final String partialText = text.substring(0, i + 1);
            KeyFrame keyFrame = new KeyFrame(timePoint, e -> label.setText(partialText));
            timeline.getKeyFrames().add(keyFrame);
            timePoint = timePoint.add(pause);
        }
        timeline.play();
    }

    private void navigateToLogin(Stage stage) {
        Authentication start = new Authentication();
        start.LoginPage(stage);
    }

    private void navigateToBudget(Stage stage, String username) {
        BudgetPlanning budget = new BudgetPlanning();
        budget.budgetButtonOnAction(stage, username);
    }

    private void navigateToTransaction(Stage stage, String username) {
        TransactionManagement transaction = new TransactionManagement();
        transaction.transactionButtonOnAction(stage, username);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Getter and Setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for budgetData
    public ArrayList<String> getBudgetData() {
        return budgetData;
    }

    public void setBudgetData(ArrayList<String> budgetData) {
        this.budgetData = budgetData;
    }
}
