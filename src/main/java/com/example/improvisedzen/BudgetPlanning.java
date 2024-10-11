package com.example.improvisedzen;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BudgetPlanning {

    private Font interMedium;
    private Stage stage;
    private String username;
    private VBox root;
    private Label titleLabel;
    private GridPane gridPane;
    private HBox hbox;
    private ComboBox<String> categoryComboBox;
    private TextField budgetField;
    private ComboBox<String> periodComboBox;
    private Button saveButton;
    private Button cancelButton;

    // Default constructor
    public BudgetPlanning() {
        this.interMedium = Font.loadFont(getClass().getResourceAsStream("/path/to/Inter-Medium.ttf"), 20);
    }

    // Parameterized constructor
    public BudgetPlanning(Stage stage, String username) {
        this();
        this.stage = stage;
        this.username = username;
    }

    public void budgetButtonOnAction(Stage stage, String username) {
        this.stage = stage;
        this.username = username;

        root = new VBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #00928F;");

        titleLabel = new Label();
        titleLabel.setFont(interMedium);
        titleLabel.setLayoutY(0);
        titleLabel.setLayoutX(0);
        titleLabel.setPrefHeight(212);
        titleLabel.setPrefWidth(1100);
        titleLabel.setAlignment(Pos.TOP_CENTER);
        titleLabel.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: white;");

        String titleText = "\"If broke people are making fun of your financial plan, \n\t\t\tyou are on the right track.\"";
        animateText(titleLabel, titleText);

        gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);

        Label categoryLabel = new Label("Choose category:");
        categoryLabel.setFont(interMedium);
        categoryLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");
        categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll("Groceries", "Utilities", "Entertainment");
        categoryComboBox.setStyle("-fx-font-family: 'Inter Medium'; -fx-font-size: 20px;");
        categoryComboBox.setPrefWidth(500);

        Label budgetLabel = new Label("Specify budget:");
        budgetLabel.setFont(interMedium);
        budgetLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        budgetField = new TextField();
        budgetField.setPromptText("Enter budget");
        budgetField.setStyle("-fx-font-family: 'Inter Medium'; -fx-font-size: 20px;");
        budgetField.setPrefWidth(500);

        Label periodLabel = new Label("Set budget period :");
        periodLabel.setFont(interMedium);
        periodLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");
        periodComboBox = new ComboBox<>();
        periodComboBox.getItems().addAll("Monthly", "Weekly");
        periodComboBox.setStyle("-fx-font-family: 'Inter Medium'; -fx-font-size: 20px;");
        periodComboBox.setPrefWidth(500);

        cancelButton = new Button("Cancel");
        cancelButton.setFont(interMedium);
        cancelButton.setStyle("-fx-background-color: #D59F0F; -fx-text-fill: white; -fx-font-size: 20px;");
        cancelButton.setOnAction(e -> navigateToDashboard(stage, username));

        saveButton = new Button("Save");
        saveButton.setFont(interMedium);
        saveButton.setStyle("-fx-background-color: #D59F0F; -fx-text-fill: white; -fx-font-size: 20px;");
        saveButton.setOnAction(e -> {
            String category = categoryComboBox.getValue();
            double budget = Double.parseDouble(budgetField.getText());
            String period = periodComboBox.getValue();

            InputData(username, category, budget);
            navigateToDashboard(stage, username);
        });

        gridPane.add(categoryLabel, 0, 0);
        gridPane.add(categoryComboBox, 1, 0);
        gridPane.add(budgetLabel, 0, 1);
        gridPane.add(budgetField, 1, 1);
        gridPane.add(periodLabel, 0, 2);
        gridPane.add(periodComboBox, 1, 2);

        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(450);
        Rectangle rectangle2 = new Rectangle();
        Rectangle rectangle3 = new Rectangle();
        rectangle3.setWidth(23);

        hbox = new HBox(rectangle, rectangle2, saveButton, rectangle3, cancelButton);

        root.getChildren().addAll(titleLabel, gridPane, hbox);

        // Add animations
        addAnimations(gridPane, hbox, categoryComboBox, budgetField, periodComboBox, saveButton, cancelButton);

        Scene scene = new Scene(root, 1100, 760);
        stage.setScene(scene);
        stage.show();
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

    private void addAnimations(GridPane gridPane, HBox hbox,
                               ComboBox<String> categoryComboBox, TextField budgetField,
                               ComboBox<String> periodComboBox, Button saveButton, Button cancelButton) {
        Duration fadeDuration = Duration.seconds(0.3);

        FadeTransition fadeInGrid = new FadeTransition(fadeDuration, gridPane);
        fadeInGrid.setFromValue(0);
        fadeInGrid.setToValue(1);

        FadeTransition fadeInHbox = new FadeTransition(fadeDuration, hbox);
        fadeInHbox.setFromValue(0);
        fadeInHbox.setToValue(1);

        FadeTransition fadeInCategoryComboBox = new FadeTransition(fadeDuration, categoryComboBox);
        fadeInCategoryComboBox.setFromValue(0);
        fadeInCategoryComboBox.setToValue(1);

        FadeTransition fadeInBudgetField = new FadeTransition(fadeDuration, budgetField);
        fadeInBudgetField.setFromValue(0);
        fadeInBudgetField.setToValue(1);

        FadeTransition fadeInPeriodComboBox = new FadeTransition(fadeDuration, periodComboBox);
        fadeInPeriodComboBox.setFromValue(0);
        fadeInPeriodComboBox.setToValue(1);

        FadeTransition fadeInSaveButton = new FadeTransition(fadeDuration, saveButton);
        fadeInSaveButton.setFromValue(0);
        fadeInSaveButton.setToValue(1);

        FadeTransition fadeInCancelButton = new FadeTransition(fadeDuration, cancelButton);
        fadeInCancelButton.setFromValue(0);
        fadeInCancelButton.setToValue(1);

        SequentialTransition sequentialTransition = new SequentialTransition(
                fadeInGrid,
                fadeInHbox,
                fadeInCategoryComboBox,
                fadeInBudgetField,
                fadeInPeriodComboBox,
                fadeInSaveButton,
                fadeInCancelButton
        );
        sequentialTransition.play();
    }

    private void navigateToDashboard(Stage stage, String username) {
        Dashboard start = new Dashboard();
        start.DashboardPage(stage, username);
    }

    private void InputData(String username, String category, double budget) {
        List<String> lines = new ArrayList<>();
        File details = new File("src/main/files/usersDetails.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(details))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (username.equals(parts[0])) {
                    switch (category) {
                        case "Groceries":
                            parts[3] = String.valueOf(budget);
                            break;
                        case "Utilities":
                            parts[4] = String.valueOf(budget);
                            break;
                        case "Entertainment":
                            parts[5] = String.valueOf(budget);
                            break;
                    }
                    line = String.join(",", parts);
                }
                lines.add(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(details))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
