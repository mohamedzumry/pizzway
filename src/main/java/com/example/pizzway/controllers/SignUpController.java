package com.example.pizzway.controllers;

import com.example.pizzway.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField txt_sign_up_username;

    @FXML
    private TextField txt_sign_up_name;

    @FXML
    private TextField txt_sign_up_phone;

    @FXML
    private TextField txt_sign_up_address;

    @FXML
    private TextField txt_sign_up_pass;

    @FXML
    private TextField txt_sign_up_confirm_pass;

    @FXML
    private Button btn_sign_up, homeButton, btn_sign_up_login;

    @FXML
    public void initialize() {
        // Attach event listener to the button
        btn_sign_up.setOnAction(event -> handleSignUp());
    }

    @FXML
    public void handleGoToLogin(ActionEvent event) {
        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("com.example.pizzway/login-view.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login-view.fxml"));
            Scene loginScene = new Scene(fxmlLoader.load());

            // Get the current stage (window)
            Stage stage = (Stage) btn_sign_up_login.getScene().getWindow();

            // Set the new scene (Login view)
            stage.setScene(loginScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleSignUp() {
        // Step 1: Get user input
        String username = txt_sign_up_username.getText().trim();
        String name = txt_sign_up_name.getText().trim();
        String phone = txt_sign_up_phone.getText().trim();
        String address = txt_sign_up_address.getText().trim();
        String password = txt_sign_up_pass.getText().trim();
        String confirmPassword = txt_sign_up_confirm_pass.getText().trim();

        // Step 2: Validate input
        if (username.isEmpty() || name.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields are required!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Passwords do not match!");
            return;
        }

        // Step 3: Save user data to file
        try {
            saveUserToFile(username, name, phone, address, password);
            showAlert(Alert.AlertType.INFORMATION, "Sign-Up Successful", "User registered successfully!");
            clearFields();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "File Error", "Unable to save user data!");
        }
    }

    private void saveUserToFile(String username, String name, String phone, String address, String password) throws IOException {
        String USER_DATA_FILE = "users.txt";
        File file = new File(USER_DATA_FILE);
        boolean newFile = file.createNewFile(); // Create file if it doesn't exist
        try (FileWriter fileWriter = new FileWriter(file, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            if (newFile) {
                printWriter.println("Username, Name, Phone, Address,Password"); // Add header if new file
            }
            printWriter.printf("%s,%s,%s,%s,%s%n", username, name, phone, address, password);
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void goToHomePage(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("home-view.fxml"));
            Scene profileScene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.setScene(profileScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txt_sign_up_username.clear();
        txt_sign_up_name.clear();
        txt_sign_up_phone.clear();
        txt_sign_up_address.clear();
        txt_sign_up_pass.clear();
        txt_sign_up_confirm_pass.clear();
    }


}
