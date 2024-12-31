package com.example.pizzway.controllers;

import com.example.pizzway.MainApp;
import com.example.pizzway.models.LoggedInCustomer;
import com.example.pizzway.models.Order;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProfileController {

    @FXML
    private Label profileLabelUsername, profileLabelName;

    @FXML
    private Accordion orderAccordion;

    @FXML
    private Button homeButton, orderNowButton, profileButtonNotifications, profileButtonLogout, profileButtonOrders, profileButtonSaved;

    LoggedInCustomer user = LoggedInCustomer.getInstance();

    public ProfileController() throws IOException, ClassNotFoundException {
    }

    @FXML
    public void initialize() {
        // Set event handlers
        homeButton.setOnAction(event -> goToHomePage());
        orderNowButton.setOnAction(event -> goToOrderNowPage());
        profileButtonLogout.setOnAction(event -> logout());

        profileLabelUsername.setText(user.getUsername());
        profileLabelName.setText(user.getName());

    }

    private void logout() {
        try {

            LoggedInCustomer user = LoggedInCustomer.getInstance();
            user.setAddress("");
            user.setUsername("");
            user.setPhone("");
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("home-view.fxml"));
            Scene homeScene = new Scene(fxmlLoader.load());

            // Get the current stage (window)
            Stage stage = (Stage) profileButtonLogout.getScene().getWindow();

            // Set the new scene
            stage.setScene(homeScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goToHomePage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("home-view.fxml"));
            Scene profileScene = new Scene(fxmlLoader.load());

            // Get the current stage (window)
            Stage stage = (Stage) homeButton.getScene().getWindow();

            // Set the new scene (Login view)
            stage.setScene(profileScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goToOrderNowPage() {
        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("com.example.pizzway/login-view.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("pizza-order-view.fxml"));
            Scene orderNowScene = new Scene(fxmlLoader.load());

            // Get the current stage (window)
            Stage stage = (Stage) orderNowButton.getScene().getWindow();

            // Set the new scene (Login view)
            stage.setScene(orderNowScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
