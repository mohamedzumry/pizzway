package com.example.pizzway.controllers;

import com.example.pizzway.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private Button profileButton, orderNowButton;

    @FXML
    public void initialize() {
        // Set event handlers
        profileButton.setOnAction(event -> goToProfilePage());
        orderNowButton.setOnAction(event -> goToOrderNowPage());


        // Initialize promotions type options
//        orderTypeComboBox.getItems().addAll("Pick Up", "Delivery");
        // Initialize payment options
//        paymentTypeComboBox.getItems().addAll("Digital Wallet", "Card");

        // Initialize toppings
        String[] toppings = {"Pepperoni", "Mushrooms", "Onions", "Olives", "Bacon", "Pineapple"};
        for (String topping : toppings) {
            CheckBox checkBox = new CheckBox(topping);
//            toppingsVBox.getChildren().add(checkBox);
        }
    }

    private void goToProfilePage(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("profile-view.fxml"));
            Scene profileScene = new Scene(fxmlLoader.load());

            // Get the current stage (window)
            Stage stage = (Stage) profileButton.getScene().getWindow();

            // Set the new scene (Login view)
            stage.setScene(profileScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goToOrderNowPage(){
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

