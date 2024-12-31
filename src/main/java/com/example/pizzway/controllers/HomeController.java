package com.example.pizzway.controllers;

import com.example.pizzway.MainApp;
import com.example.pizzway.models.Feedback;
import com.example.pizzway.models.LoggedInCustomer;
import com.example.pizzway.models.Promotion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;

public class HomeController {

    @FXML
    private Button profileButton, orderNowButton;

    @FXML
    private VBox promotionsVBox;

    @FXML
    private HBox feedBacksHBox;

    LoggedInCustomer user = LoggedInCustomer.getInstance();

    @FXML
    public void initialize() {
        // Set event handlers
        profileButton.setOnAction(event -> goToProfilePage());
        orderNowButton.setOnAction(event -> goToOrderNowPage());

        // Initialize promotions
        Promotion[] promotions = {
                new Promotion("New Year Bonanza", "Start the New Year with a bang! Enjoy 10% off on all products in our store from January 1st to January 15th. Don't miss out on these incredible savings!"),
                new Promotion("Genza Toppings", "Bringing back our special Pizzway 'Genza' Toppings, only till 10th of January"),
        };
        for (Promotion promo : promotions) {
            promotionsVBox.setSpacing(10);
            promotionsVBox.getChildren().add(createPromotionCard(promo));
        }

        // Initialize Feedbacks
        Feedback feedback1 = new Feedback(1, "Great service, highly recommend!");
        Feedback feedback2 = new Feedback(2, "The product quality is excellent.");
        Feedback feedback3 = new Feedback(3, "Fast shipping and good customer support.");
        Feedback[] feedbacks = {feedback1, feedback2, feedback3};

        for (Feedback fb : feedbacks) {
            feedBacksHBox.setSpacing(10);
            feedBacksHBox.setAlignment(Pos.CENTER);
            feedBacksHBox.getChildren().add(createFeedBackCard(fb));
        }
    }

    private VBox createPromotionCard(Promotion promo) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(10));
        card.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5), Insets.EMPTY)));
        Label titleLabel = new Label(promo.getTitle());
        titleLabel.setFont(new Font("Arial", 18));
        titleLabel.setTextFill(Color.DARKBLUE);
        Label descriptionLabel = new Label(promo.getDescription());
        descriptionLabel.setWrapText(true);
        card.getChildren().addAll(titleLabel, descriptionLabel);
        VBox.setVgrow(card, Priority.ALWAYS);
        return card;
    }

    private VBox createFeedBackCard(Feedback feedback) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(10));
        card.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5), Insets.EMPTY)));
        card.setMinWidth(150);
        Label feedbackLabel = new Label(feedback.getFeedback());
        feedbackLabel.setWrapText(true);
        card.getChildren().addAll(feedbackLabel);
        return card;
    }

    private void goToProfilePage() {
        try {

            if (user.getUsername() == null || user.getUsername().isEmpty()) {
                goToLoginPage();
                return;
            }
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

    private void goToOrderNowPage() {
        try {
            if (user.getUsername() == null || user.getUsername().isEmpty()) {
                goToLoginPage();
                return;
            }
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

    private void goToLoginPage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login-view.fxml"));
            Scene loginScene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) profileButton.getScene().getWindow();

            // Set the new scene (Login view)
            stage.setScene(loginScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


