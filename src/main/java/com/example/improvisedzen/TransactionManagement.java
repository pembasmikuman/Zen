package com.example.improvisedzen;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
In  username_transactions file the sequence is like this :
    transaction id/username/type/amount/date/description/category
*/

/*
In  usersDetail file the sequence is like this :
    username/income/expenses/groceries/utilities/entertainment
*/

public class TransactionManagement {

    public void transactionButtonOnAction(Stage stage, String username) {

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000));
        FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(400));
        FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(400));
        FadeTransition fadeTransition3 = new FadeTransition(Duration.millis(400));
        FadeTransition fadeTransition4 = new FadeTransition(Duration.millis(700));

        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(1);
        fadeTransition2.setFromValue(0);
        fadeTransition2.setToValue(1);
        fadeTransition3.setFromValue(0);
        fadeTransition3.setToValue(1);
        fadeTransition4.setFromValue(0);
        fadeTransition4.setToValue(1);

        fadeTransition.setAutoReverse(true);
        fadeTransition1.setAutoReverse(true);
        fadeTransition2.setAutoReverse(true);
        fadeTransition3.setAutoReverse(true);
        fadeTransition4.setAutoReverse(true);

        BorderPane root = new BorderPane();
        root.setPrefHeight(760.0);
        root.setPrefWidth(1080.0);

        AnchorPane leftAnchorPane = new AnchorPane();
        leftAnchorPane.setPrefHeight(760.0);
        leftAnchorPane.setPrefWidth(200.0);

        Rectangle rectangle = new Rectangle();
        rectangle.setFill(Color.web("#00928f"));
        rectangle.setHeight(760.0);
        rectangle.setWidth(200.0);

        Label choose = new Label("Choose:");
        choose.setFont(Font.font("Inter", FontWeight.MEDIUM, 12));
        choose.setLayoutX(25);
        choose.setLayoutY(119);

        ChoiceBox<String> option = new ChoiceBox <String>();
        option.setLayoutX(25.0);
        option.setLayoutY(142.0);
        option.setPrefWidth(150);
        option.setStyle("-fx-background-color: #016664; -fx-text-fill: white;");
        ObservableList<String> oslist = option.getItems();
        oslist.addAll("Add Transaction",  "View Transaction");

        ToggleButton backButton = new ToggleButton("BACK");
        backButton.setPrefWidth(105);
        backButton.setPrefHeight(25);
        backButton.setLayoutX(48);
        backButton.setLayoutY(707);
        backButton.getStyleClass().add("backButton-style");

        leftAnchorPane.getChildren().addAll(rectangle, choose, option, backButton);
        root.setLeft(leftAnchorPane);

        //Right
        AnchorPane rightAnchorPane = new AnchorPane();
        rightAnchorPane.setPrefHeight(760.0);
        rightAnchorPane.setPrefWidth(900.0);

        Rectangle rectangleRight = new Rectangle(900,  760);
        rectangleRight.setFill(Color.web("#008f8aba"));
        rectangleRight.setStroke(Color.BLACK);
        rectangleRight.setStrokeType(StrokeType.INSIDE);
        rectangleRight.setStrokeWidth(0.0);

        Label title = new Label("Transaction Manager");
        title.setPrefWidth(791);
        title.setPrefHeight(74);
        title.setAlignment(Pos.CENTER);
        title.setFont(Font.font("Inter", FontWeight.BOLD, 60));
        title.setTextFill(Color.WHITE);
        title.setLayoutX(75);
        title.setLayoutY(24);

        Label chooseType = new Label("Choose type of transaction:");
        chooseType.setFont(Font.font("Inter", FontWeight.MEDIUM, 20));
        chooseType.setTextFill(Color.WHITE);
        chooseType.setPrefWidth(287);
        chooseType.setPrefHeight(45);
        chooseType.setLayoutX(126);
        chooseType.setLayoutY(164);

        ChoiceBox<String> typeBox = new ChoiceBox<String>();
        typeBox.setPrefWidth(267);
        typeBox.setPrefHeight(35);
        typeBox.setStyle("-fx-background-color: #008a87; -body-text-color: white; -fx-font-size: 17");
        typeBox.setLayoutX(446);
        typeBox.setLayoutY(170.0);
        ObservableList<String> oslistForType = typeBox.getItems();
        oslistForType.addAll("Income", "Expenses");

        Label setAmount = new Label("Amount of transaction:");
        setAmount.setFont(Font.font("Inter", FontWeight.MEDIUM, 20));
        setAmount.setTextFill(Color.WHITE);
        setAmount.setPrefWidth(287);
        setAmount.setPrefHeight(45);
        setAmount.setLayoutX(126);
        setAmount.setLayoutY(213);

        TextField dummy = new TextField("RM ");
        dummy.setPrefWidth(267);
        dummy.setPrefHeight(35);
        dummy.setLayoutY(216);
        dummy.setLayoutX(446);
        dummy.setEditable(false);
        dummy.setStyle("-fx-background-color: #008f8a; -fx-border-radius: 12; -fx-text-fill: white; -fx-font-size: 17;");

        TextField amountField = new TextField();
        amountField.setPrefWidth(227);
        amountField.setPrefHeight(35);
        amountField.setLayoutY(216);
        amountField.setLayoutX(486);
        amountField.setStyle("-fx-background-color: #008f8a; -fx-border-radius: 12; -fx-text-fill: white; -fx-font-size: 17;");

        Label setDate = new Label("Date:");
        setDate.setFont(Font.font("Inter", FontWeight.MEDIUM, 20));
        setDate.setTextFill(Color.WHITE);
        setDate.setPrefWidth(287);
        setDate.setPrefHeight(45);
        setDate.setLayoutX(126);
        setDate.setLayoutY(262);

        DatePicker datePicker = new DatePicker();
        datePicker.setPrefWidth(267);
        datePicker.setPrefHeight(35);
        datePicker.setLayoutY(262);
        datePicker.setLayoutX(446);
        datePicker.setStyle("-fx-border-radius: 12; -fx-font-size: 15;");

        Label setDescription = new Label("Description:");
        setDescription.setFont(Font.font("Inter", FontWeight.MEDIUM, 20));
        setDescription.setTextFill(Color.WHITE);
        setDescription.setPrefWidth(287);
        setDescription.setPrefHeight(45);
        setDescription.setLayoutX(126);
        setDescription.setLayoutY(311);

        TextField descriptionField = new TextField();
        descriptionField.setPrefWidth(267);
        descriptionField.setPrefHeight(35);
        descriptionField.setLayoutY(311);
        descriptionField.setLayoutX(446);
        descriptionField.setStyle("-fx-background-color: #008f8a; -fx-border-radius: 12; -fx-text-fill: white; -fx-font-size: 15;");

        Label categoryType = new Label("Choose expenses category:");
        categoryType.setFont(Font.font("Inter", FontWeight.MEDIUM, 20));
        categoryType.setTextFill(Color.WHITE);
        categoryType.setPrefWidth(287);
        categoryType.setPrefHeight(45);
        categoryType.setLayoutX(126);
        categoryType.setLayoutY(360);

        ChoiceBox<String> categoryTypeBox = new ChoiceBox<String>();
        categoryTypeBox.setPrefWidth(267);
        categoryTypeBox.setPrefHeight(35);
        categoryTypeBox.setStyle("-fx-background-color: #008a87; -body-text-color: white; -fx-font-size: 17");
        categoryTypeBox.setLayoutX(446);
        categoryTypeBox.setLayoutY(360.0);
        ObservableList<String> oslistForCType = categoryTypeBox.getItems();
        oslistForCType.addAll("Groceries", "Utilities", "Entertainment");

        ToggleButton saveButton = new ToggleButton("SAVE");
        saveButton.setPrefWidth(105);
        saveButton.setPrefHeight(25);
        saveButton.setLayoutX(707);
        saveButton.setLayoutY(707);
        saveButton.getStyleClass().add("saveButton-style");

        ToggleButton editButton = new ToggleButton("EDIT");
        editButton.setPrefWidth(105);
        editButton.setPrefHeight(25);
        editButton.setLayoutY(707);
        editButton.setLayoutX(680);
        editButton.getStyleClass().add("editButton-style");

        ScrollPane viewing = new ScrollPane();
        viewing.setPrefHeight(520);
        viewing.setPrefWidth(617);
        viewing.setLayoutX(160);
        viewing.setLayoutY(142);

        TableView<List<String>> tableView = new TableView<>();
        tableView.setPrefHeight(520.0);
        tableView.setPrefWidth(617.0);
        tableView.getStyleClass().add("tableView");

        TableColumn<List<String>, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setPrefWidth(86.0);
        typeColumn.getStyleClass().add("column");
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));

        TableColumn<List<String>, String> amountColumn = new TableColumn<>("Amount");
        amountColumn.setPrefWidth(124.0);
        amountColumn.getStyleClass().add("column");
        amountColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));

        TableColumn<List<String>, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setPrefWidth(94.0);
        dateColumn.getStyleClass().add("column");
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));

        TableColumn<List<String>, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setPrefWidth(190.0);
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        descriptionColumn.getStyleClass().add("column");

        TableColumn<List<String>, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setPrefWidth(112.0);
        categoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(4)));
        categoryColumn.getStyleClass().add("column");

        tableView.getColumns().addAll(typeColumn, amountColumn, dateColumn, descriptionColumn, categoryColumn);
        viewing.setContent(tableView);
        viewing.setFitToWidth(true);

        fadeTransition.setNode(option);
        fadeTransition1.setNode(saveButton);
        fadeTransition2.setNode(editButton);
        fadeTransition3.setNode(viewing);
        fadeTransition4.setNode(title);

        chooseType.setVisible(false);
        typeBox.setVisible(false);
        dummy.setVisible(false);
        setAmount.setVisible(false);
        amountField.setVisible(false);
        setDate.setVisible(false);
        datePicker.setVisible(false);
        setDescription.setVisible(false);
        descriptionField.setVisible(false);
        categoryType.setVisible(false);
        categoryTypeBox.setVisible(false);
        saveButton.setVisible(false);
        viewing.setVisible(false);
        editButton.setVisible(false);

        rightAnchorPane.getChildren().addAll(
                rectangleRight, title, chooseType,
                typeBox, setAmount, dummy,
                amountField, setDate, datePicker,
                setDescription, descriptionField,
                categoryType, categoryTypeBox, saveButton,
                viewing, editButton
        );
        root.setRight(rightAnchorPane);

        //Function
        option.setOnAction(e -> {

            String selectedValue = option.getValue();

            if (selectedValue.equals("Add Transaction")) {

                title.setText("Add Transaction");
                fadeTransition4.play();

                viewing.setVisible(false);
                typeBox.setVisible(true);
                chooseType.setVisible(true);
                editButton.setVisible(false);


                // Prompt user to choose income or expense
                typeBox.setOnAction(ex -> {

                    String selectedValueForType = typeBox.getValue();
                    switch(selectedValueForType) {

                        case "Income":
                            categoryTypeBox.setVisible(false);
                            categoryType.setVisible(false);
                            setAmount.setVisible(true);
                            dummy.setVisible(true);
                            amountField.setVisible(true);

                            if (amountField.getText() != null) {

                                setDate.setVisible(true);
                                datePicker.setVisible(true);

                                if (datePicker.getValue() == null) {

                                    setDescription.setVisible(true);
                                    descriptionField.setVisible(true);

                                    if (descriptionField.getText().isBlank()) {

                                        fadeTransition1.play();
                                        saveButton.setVisible(true);
                                        saveButton.setOnAction(exy -> {

                                            writeIncome(
                                                    username, selectedValueForType,
                                                    Double.parseDouble(amountField.getText()),
                                                    String.valueOf(datePicker.getValue()),
                                                    descriptionField.getText()
                                            );
                                            navigateToDashboard(stage, username);
                                        });
                                    }
                                }
                            }
                            break;

                        case "Expenses":

                            setAmount.setVisible(true);
                            dummy.setVisible(true);
                            amountField.setVisible(true);
                            setDate.setVisible(true);
                            datePicker.setVisible(true);
                            setDescription.setVisible(true);
                            descriptionField.setVisible(true);
                            categoryType.setVisible(true);
                            categoryTypeBox.setVisible(true);
                            saveButton.setVisible(true);
                            fadeTransition1.play();

                            categoryTypeBox.setOnAction(exy -> {
                                String selectedValueForCategory = categoryTypeBox.getValue();

                                if (selectedValueForCategory != null) {
                                    saveButton.setOnAction(exyz -> {

                                        writeExpense(username, selectedValueForType,
                                                Double.parseDouble(amountField.getText()),
                                                String.valueOf(datePicker.getValue()),
                                                descriptionField.getText(),
                                                selectedValueForCategory);
                                        navigateToDashboard(stage, username);
                                    });
                                }

                            });
                            break;
                    }
                });

            }

            else if (selectedValue.equals("View Transaction"))  {

                title.setText("View Transaction");
                fadeTransition4.play();

                chooseType.setVisible(false);
                typeBox.setVisible(false);
                dummy.setVisible(false);
                setAmount.setVisible(false);
                amountField.setVisible(false);
                setDate.setVisible(false);
                datePicker.setVisible(false);
                setDescription.setVisible(false);
                descriptionField.setVisible(false);
                categoryType.setVisible(false);
                categoryTypeBox.setVisible(false);
                saveButton.setVisible(false);
                viewing.setVisible(true);
                fadeTransition3.play();

                ObservableList<List<String>> data = FXCollections.observableArrayList();

                try (BufferedReader br = new BufferedReader(new FileReader(createFile(username)))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] values = line.split(",");

                        List<String> row = new ArrayList<>();
                        row.add(values[2]);
                        row.add("RM " +values[3]);
                        row.add(values[4]);
                        row.add(values[5]);
                        row.add(values[6]);
                        data.add(row);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                tableView.setItems(data);
                editButton.setVisible(true);
                fadeTransition2.play();
            }

        });

        backButton.setOnAction(e -> {

            navigateToDashboard(stage, username);
        });

        editButton.setOnAction(e  -> {

            try {
                BufferedReader reader = new BufferedReader(new FileReader(createFile(username)));
                String line;
                String[] parts;
                String temp = "";
                List<String> transactions = new ArrayList<>();

                while ((line = reader.readLine()) != null) {
                    parts = line.split(",");
                    transactions.add("Type: " +parts[2]+ " Amount: RM"+parts[3]+ " Date: "+parts[4]+ " Description: "+parts[5]+ " Category: "+parts[6]);
                    temp = parts[0]+ "," +parts[2]+ "," +parts[3]+ "," +parts[4]+ "," +parts[5]+ "," +parts[6];
                }

                viewing.setVisible(false);
                editButton.setVisible(false);

                // Display current transactions in a ChoiceDialog
                ChoiceDialog<String> dialog = new ChoiceDialog<>(null, transactions);
                dialog.setTitle("Edit Transaction");
                dialog.setHeaderText("Select a transaction to edit:");
                dialog.setContentText("Transactions:");
                Optional<String> selectedTransaction = dialog.showAndWait();

                //Edit the selected transaction
                if (selectedTransaction.isPresent()) {

                    parts = temp.split(",");
                    String selectedValueForType = parts[1];
                    int id = Integer.valueOf(parts[0]);

                    switch(selectedValueForType) {

                        case "Income":

                            categoryTypeBox.setVisible(false);
                            categoryType.setVisible(false);
                            chooseType.setVisible(true);
                            typeBox.setVisible(true);
                            setAmount.setVisible(true);
                            dummy.setVisible(true);
                            amountField.setVisible(true);
                            setDate.setVisible(true);
                            datePicker.setVisible(true);
                            setDescription.setVisible(true);
                            descriptionField.setVisible(true);
                            saveButton.setVisible(true);

                            typeBox.setValue(selectedValueForType);
                            amountField.setText(parts[2]);
                            datePicker.setValue(LocalDate.parse(parts[3]));
                            descriptionField.setText(parts[4]);

                            typeBox.setOnAction(ex -> {

                                if (typeBox.getValue().equals("Income")) {
                                    categoryType.setVisible(false);
                                    categoryTypeBox.setVisible(false);
                                }
                                else {
                                    categoryType.setVisible(true);
                                    categoryTypeBox.setVisible(true);
                                }
                            });

                            saveButton.setOnAction(ex -> {

                                editIncome(
                                        id,
                                        username, selectedValueForType,
                                        Double.parseDouble(amountField.getText()),
                                        String.valueOf(datePicker.getValue()),
                                        descriptionField.getText()
                                );
                                navigateToDashboard(stage, username);
                            });
                            break;

                        case "Expenses":

                            chooseType.setVisible(true);
                            typeBox.setVisible(true);
                            setAmount.setVisible(true);
                            dummy.setVisible(true);
                            amountField.setVisible(true);
                            setDate.setVisible(true);
                            datePicker.setVisible(true);
                            setDescription.setVisible(true);
                            descriptionField.setVisible(true);
                            categoryType.setVisible(true);
                            categoryTypeBox.setVisible(true);
                            saveButton.setVisible(true);

                            typeBox.setValue(selectedValueForType);
                            amountField.setText(parts[2]);
                            datePicker.setValue(LocalDate.parse(parts[3]));
                            descriptionField.setText(parts[4]);
                            categoryTypeBox.setValue(parts[5]);

                            categoryTypeBox.setOnAction(exy -> {

                                String selectedValueForCategory = categoryTypeBox.getValue();
                            });

                            saveButton.setOnAction(exyz -> {

                                String selectedValueForCategory = categoryTypeBox.getValue();
                                editExpense(id, username, selectedValueForType,
                                        Double.parseDouble(amountField.getText()),
                                        String.valueOf(datePicker.getValue()),
                                        descriptionField.getText(),
                                        selectedValueForCategory);
                                navigateToDashboard(stage, username);
                            });
                            break;
                    }

                }

                System.out.println("Success");
            } catch (IOException ex) {
                System.out.println("Error reading from or writing to transactions.txt");
            }

        });

        Scene scene = new Scene(root,1100, 760);
        fadeTransition.play();
        fadeTransition4.play();
        scene.getStylesheets().add(getClass().getResource("TM.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Zen.");
        stage.show();
    }

    private void editExpense(int id, String username, String type, double amount, String date, String description, String category) {

        File userTransactionsFile = createFile(username);
        List<String> existingTransactions = new ArrayList<>();
        double initialAmount = 0;
        String[] parts;

        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(userTransactionsFile));

            while ((line = reader.readLine()) != null) {
                existingTransactions.add(line);
                parts = line.split(",");

                if (parts[0].equals(String.valueOf(id))) {

                    initialAmount = Double.parseDouble(parts[3]);
                }
            }
            reader.close();


            System.out.println("Running input data method");

            System.out.println("Transaction details added to " + username + "_transactions.txt");
        } catch (IOException ex) {
            System.out.println("Error writing to " + username + "_transactions.txt");
        }

        for (int i=0; i< existingTransactions.size(); i++) {
            String line = existingTransactions.get(i);
            String[] values = line.split(",");

            if (values[0].equals(String.valueOf(id))) {

                values[2] = type;
                values[3] = String.valueOf(amount);
                values[4] = date;
                values[5] = description;
                values[6] = category;
                existingTransactions.set(i,String.join(",", values));
            }
        }

        // Write existing content and new transaction details back to transactions.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userTransactionsFile))) {

            for (String line : existingTransactions) {
                writer.write(line);
                writer.newLine();
            }
            InputDataforEdit(username, category, amount, initialAmount, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void editIncome(int id, String username, String type, double amount, String date, String description) {

        File userTransactionsFile = createFile(username);
        String category = "";
        String[] parts;
        double initialAmount = 0;
        List<String> existingTransactions = new ArrayList<>();

        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(userTransactionsFile));

            while ((line = reader.readLine()) != null) {
                existingTransactions.add(line);
                parts = line.split(",");

                if (parts[0].equals(String.valueOf(id))) {

                    initialAmount = Double.parseDouble(parts[3]);
                }
            }
            reader.close();

            System.out.println("Transaction details read at " + username + "_transactions.txt");
        } catch (IOException ex) {
            System.out.println("Error reading to " + username + "_transactions.txt");
        }

        for (int i=0; i< existingTransactions.size(); i++) {
            String line = existingTransactions.get(i);
            String[] values = line.split(",");

            if (values[0].equals(String.valueOf(id))) {

                values[2] = type;
                values[3] = String.valueOf(amount);
                values[4] = date;
                values[5] = description;
                existingTransactions.set(i,String.join(",", values));
            }
        }

        // Write existing content and new transaction details back to transactions.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userTransactionsFile))) {

            for (String line : existingTransactions) {
                writer.write(line);
                writer.newLine();
            }
            InputDataforEdit(username, category, amount, initialAmount, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeIncome(String username, String type, double amount, String date, String description) {

        File userTransactionsFile = createFile(username);
        String category = "";

        try {
            List<String> existingTransactions = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(userTransactionsFile));
            String line;
            int id = 0;

            while ((line = reader.readLine()) != null) {
                existingTransactions.add(line);
                String[] parts = line.split(",");
                id = Integer.valueOf(parts[0]);
                id++;
            }
            reader.close();

            // Write existing content and new transaction details back to transactions.txt
            BufferedWriter writer = new BufferedWriter(new FileWriter(userTransactionsFile));
            for (String transaction : existingTransactions) {
                writer.write(transaction);
                writer.newLine();
            }
            writer.write(id + "," + username + "," + type + "," + amount + "," + date + "," + description + ", -");
            writer.newLine();
            writer.close();
            InputData(username, category, amount, type);
            System.out.println("Running input data method");
            System.out.println("Transaction details added to " + username + "_transactions.txt");
        } catch (IOException ex) {
            System.out.println("Error writing to " + username + "_transactions.txt");
        }

    }

     private void writeExpense(String username, String type, double amount, String date, String description, String category) {

        File userTransactionsFile = createFile(username);

        try {

            List<String> existingTransactions = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(userTransactionsFile));
            String line;
            int id  = 0;

            while ((line = reader.readLine()) != null) {
                existingTransactions.add(line);
                String[] parts = line.split(",");
                id = Integer.valueOf(parts[0]);
                id++;
            }
            reader.close();

            // Write existing content and new transaction details back to transactions.txt
            BufferedWriter writer = new BufferedWriter(new FileWriter(userTransactionsFile));
            for (String transaction : existingTransactions) {
                writer.write(transaction);
                writer.newLine();
            }
            writer.write(id+ "," +username+ "," +type+ "," +amount+ "," +date+ "," +description+ "," +category);
            writer.newLine();
            writer.close();
            InputData(username, category, amount, type);
            System.out.println("Running input data method");
            System.out.println("Transaction details added to " + username + "_transactions.txt");
        } catch (IOException ex) {
            System.out.println("Error writing to " + username + "_transactions.txt");
        }
    }

    private void InputData(String username, String category, double amount, String type) {

        List <String> lines = new ArrayList<>();
        File details = new File("src/main/files/usersDetails.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(details));) {

            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i=0; i< lines.size(); i++) {

            String[] parts = lines.get(i).split(",");
            if (username.equals(parts[0])) {

                switch (type) {
                    case "Income":
                        double tempIncome = Double.parseDouble(parts[1]) + amount;
                        parts[1] =  String.valueOf(tempIncome);
                        break;

                    case "Expenses":
                        switch (category) {

                            case "Groceries":
                                double tempGroceries = Double.parseDouble(parts[3]);
                                System.out.println(tempGroceries);
                                tempGroceries -= amount;
                                parts[3] = String.valueOf(tempGroceries);
                                break;
                            case "Utilities":
                                double tempUtilities = Double.parseDouble(parts[4]);
                                tempUtilities -= amount;
                                parts[4] = String.valueOf(tempUtilities);
                                break;
                            case "Entertainment":
                                double tempEntertainment = Double.parseDouble(parts[5]);
                                tempEntertainment -= amount;
                                parts[5] = String.valueOf(tempEntertainment);
                                break;
                        }
                        tempIncome = Double.parseDouble(parts[1]);
                        tempIncome -= amount;
                        double tempExpense = Double.parseDouble(parts[2]);
                        tempExpense += amount;
                        parts[1] = String.valueOf(tempIncome);
                        parts[2] = String.valueOf(tempExpense);
                        break;
                }
                lines.set(i, String.join(",", parts));
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(details))) {

            for (String line: lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void InputDataforEdit(String username, String category, double amount, double initialAmount, String type) {

        System.out.println(initialAmount);
        System.out.println(amount);

        List <String> lines = new ArrayList<>();
        File details = new File("src/main/files/usersDetails.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(details));) {

            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i=0; i< lines.size(); i++) {

            String[] parts = lines.get(i).split(",");
            if (username.equals(parts[0])) {


                switch (type) {
                    case "Income":
                        parts[1] =  String.valueOf(Double.parseDouble(parts[1]) - initialAmount + amount);
                        break;

                    case "Expenses":
                        switch (category) {

                            case "Groceries":
                                double tempGroceries = Double.parseDouble(parts[3]);
                                tempGroceries -= amount;
                                parts[3] = String.valueOf(tempGroceries);
                                break;
                            case "Utilities":
                                double tempUtilities = Double.parseDouble(parts[4]);
                                tempUtilities -= amount;
                                parts[4] = String.valueOf(tempUtilities);
                                break;
                            case "Entertainment":
                                double tempEntertainment = Double.parseDouble(parts[5]);
                                tempEntertainment -= amount;
                                parts[5] = String.valueOf(tempEntertainment);
                                break;
                        }

                        if (initialAmount < amount) {
                            parts[1] = String.valueOf(Double.parseDouble(parts[1]) + initialAmount - amount);
                            parts[2] = String.valueOf(Double.parseDouble(parts[2]) - initialAmount + amount);
                        }
                        else {
                            parts[1] = String.valueOf(Double.parseDouble(parts[1]) + initialAmount - amount);
                            parts[2] = String.valueOf(Double.parseDouble(parts[2]) - initialAmount + amount);
                        }
                        break;
                }
                lines.set(i, String.join(",", parts));
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(details))) {

            for (String line: lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File createFile(String username) {

        File userTransactionsFile = new File("src/main/files/" +username+ "_transactions.txt");

        if (!userTransactionsFile.exists()) {
            try {
                userTransactionsFile.createNewFile();
                System.out.println("Created " + username + "_transactions.txt");
            } catch (IOException ex) {
                System.out.println("Error creating " + username + "_transactions.txt");
            }
        }
        return userTransactionsFile;
    }

    public void navigateToDashboard(Stage stage, String username) {
        Dashboard dashboard = new Dashboard();
        dashboard.DashboardPage(stage,username);
    }

}
