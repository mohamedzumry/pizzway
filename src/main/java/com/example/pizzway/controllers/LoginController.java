package com.example.pizzway.controllers;

import com.example.pizzway.MainApp;
import com.example.pizzway.models.LoggedInCustomer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Button login_button_signup, homeButton;

    @FXML
    private void goToSignup(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("signup-view.fxml"));
            Scene signUpScene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) login_button_signup.getScene().getWindow();

            stage.setScene(signUpScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogin() {
        String username = txt_login_username.getText();
        String password = txt_login_pass.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Validation Error", "Please enter both username and password.");
            return;
        } else if(username.equals("admin") && password.equals("admin123")){
            goToAdminPage();
        }else if (authenticateUser(username, password)) {
            showAlert("Login Successful", "Welcome, " + username + "!");
            handleGoToOrderPage();
        } else {
            showAlert("Login Failed", "Invalid username or password.");
        }
    }

    private boolean authenticateUser(String username, String password) {
        String filePath = "users.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length == 5) {
                    String storedUsername = userDetails[0].trim();
                    String storedPassword = userDetails[4].trim();
                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        LoggedInCustomer user = LoggedInCustomer.getInstance();
                        user.setUsername(username);
                        user.setName(userDetails[1]);
                        user.setAddress(userDetails[3]);
                        user.setPhone(userDetails[2]);
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            showAlert("File Error", "Error reading users file: " + e.getMessage());
        }
        return false;
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

            Stage stage = (Stage) txt_login_pass.getScene().getWindow();

            stage.setScene(loginScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void goToAdminPage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("admin-view.fxml"));
            Scene adminScene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) txt_login_pass.getScene().getWindow();

            stage.setScene(adminScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
