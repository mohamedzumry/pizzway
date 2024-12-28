package com.example.pizzway.controllers;

import com.example.pizzway.MainApp;
import com.example.pizzway.models.LoggedInCustomer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txt_login_username;

    @FXML
    private PasswordField txt_login_pass;

    @FXML
    private void handleLogin() {
        String username = txt_login_username.getText();
        String password = txt_login_pass.getText();

        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Validation Error", "Please enter both username and password.");
            return;
        }

        if (authenticateUser(username, password)) {
            LoggedInCustomer user = LoggedInCustomer.getInstance();
            user.setUsername(username);
            user.setAddress("123 Main Street");
            user.setPhone("123-456-7890");

            showAlert("Login Successful", "Welcome, " + username + "!");
            handleGoToOrderPage();
        } else {
            showAlert("Login Failed", "Invalid username or password.");
        }
    }

    private boolean authenticateUser(String username, String password) {
        String filePath = "users.txt"; // Update with the actual path to your users.txt file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip header line
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length == 5) {
                    String storedUsername = userDetails[0].trim();
                    String storedPassword = userDetails[4].trim();
                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        return true; // User authenticated
                    }
                }
            }
        } catch (IOException e) {
            showAlert("File Error", "Error reading users file: " + e.getMessage());
        }
        return false; // Authentication failed
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void handleGoToOrderPage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("pizza-order-view.fxml"));
            Scene loginScene = new Scene(fxmlLoader.load());

            // Get the current stage (window)
            Stage stage = (Stage) txt_login_pass.getScene().getWindow();

            // Set the new scene (Login view)
            stage.setScene(loginScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
