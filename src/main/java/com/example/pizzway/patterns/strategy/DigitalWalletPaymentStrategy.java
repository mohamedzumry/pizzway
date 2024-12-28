package com.example.pizzway.patterns.strategy;


import com.example.pizzway.models.LoggedInCustomer;
import com.example.pizzway.patterns.command.CommandInvoker;
import com.example.pizzway.patterns.command.PlaceOrderCommand;
import javafx.scene.control.Alert;

public class DigitalWalletPaymentStrategy implements PaymentStrategy {
    private double totalPrice;
    private LoggedInCustomer user;
    PlaceOrderCommand poc;
    CommandInvoker cmdInv;

    public DigitalWalletPaymentStrategy(LoggedInCustomer user, double totalPrice, PlaceOrderCommand poc, CommandInvoker cmdInv) {
        this.totalPrice = totalPrice;
        this.user = user;
        this.poc = poc;
        this.cmdInv = cmdInv;
    }

    @Override
    public void pay(double amount) {
        if (user.getLoyaltyPoints() >= Math.round(totalPrice)) {
            user.setLoyaltyPoints(user.getLoyaltyPoints() - (int) Math.round(totalPrice));
            cmdInv.executeCommand(poc);
            showAlert(Alert.AlertType.INFORMATION, "Order Placed", "Your order has been placed successfully!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Payment Failed", "Your wallet balance is not enough for the order payment. Your current balance is " + user.getLoyaltyPoints());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

