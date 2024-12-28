package com.example.pizzway.patterns.command;

import com.example.pizzway.models.Order;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PlaceOrderCommand implements Command {
    private Order order;

    public PlaceOrderCommand(Order order) {
        this.order = order;
    }

    @Override
    public void execute() {
        try {
            saveOrderToFile(order);
        } catch (IOException e) {
            showAlert();
        }
    }

    @Override
    public void undo() {
        System.out.println("Order canceled: " + order);
        // Code to cancel the order (e.g., remove from database or update status)
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("File Error");
        alert.setHeaderText(null);
        alert.setContentText("Unable to save order data!");
        alert.showAndWait();
    }

    private void saveOrderToFile(Order order) throws IOException {
        String ORDER_DATA_FILE = "orders.txt";
        File file = new File(ORDER_DATA_FILE);
        boolean newFile = file.createNewFile(); // Create file if it doesn't exist
        try (FileWriter fileWriter = new FileWriter(file, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            if (newFile) {
                printWriter.println("Order Summary, Order Type, Payment Type, Customer Name, Customer Phone, Customer Address"); // Add header if new file
            }
            printWriter.printf("%s, %s, %s, %s, %s, %s%n", order.getOrderSummary(), order.getOrderType(), order.getPaymentType(), order.getCustomerName(), order.getCustomerPhone(), order.getCustomerAddress());
        }
    }
}

