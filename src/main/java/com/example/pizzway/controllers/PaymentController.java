package com.example.pizzway.controllers;

import com.example.pizzway.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PaymentController {

    @FXML
    private Button cancelOrderButton, confirmPaymentButton;

    @FXML
    public void initialize() {
        // Set event handlers
        cancelOrderButton.setOnAction(event -> cancelOrder());
        confirmPaymentButton.setOnAction(event -> confirmPayment());
    }

    private void confirmPayment(){

    }

    private void cancelOrder() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("home-view.fxml"));
            Scene homeScene = new Scene(fxmlLoader.load());
            // Get the current stage (window)
            Stage stage = (Stage) cancelOrderButton.getScene().getWindow();
            // Set the new scene (Home view)
            stage.setScene(homeScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
