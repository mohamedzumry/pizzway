package com.example.pizzway.patterns.strategy;


import com.example.pizzway.patterns.command.CommandInvoker;
import com.example.pizzway.patterns.command.PlaceOrderCommand;
import javafx.scene.control.Alert;

public class CardPaymentStrategy implements PaymentStrategy {
    private String cardNumber;
    private PlaceOrderCommand poc;
    private CommandInvoker cmdInv;

    public CardPaymentStrategy(String cardNumber, PlaceOrderCommand poc, CommandInvoker cmdInv) {
        this.cardNumber = cardNumber;
        this.poc = poc;
        this.cmdInv = cmdInv;
    }

    @Override
    public void pay(double amount) {
        cmdInv.executeCommand(poc);
        showAlert();
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment Successful");
        alert.setHeaderText(null);
        alert.setContentText("Your payment is success and order is placed!");
        alert.showAndWait();
    }
}

