package com.example.pizzway.patterns.command;

import com.example.pizzway.models.Order;
import com.example.pizzway.patterns.observer.UserNotification;
import javafx.scene.control.Alert;

import java.io.*;

public class PlaceOrderCommand implements Command {
    private Order order;

    public PlaceOrderCommand(Order order) {
        this.order = order;
    }

    @Override
    public void execute() {
        try {
            saveOrderToFile(order);
            // Register observers
            UserNotification userNotification = new UserNotification(order.getCustomerUserName());
            order.registerObserver(userNotification);

            // Simulate status updates
            updateOrderStatus("Preparing");
            updateOrderStatus("Baking");
            updateOrderStatus("Ready for Delivery");
            updateOrderStatus("Delivered");
        } catch (IOException e) {
            showAlert();
        }
    }

    @Override
    public void undo() {
        System.out.println("Order canceled: " + order);
        // Code to cancel the order (e.g., remove from database or update status)
    }

    private void updateOrderStatus(String newStatus) {
        if (order != null) {
            order.setOrderStatus(newStatus);
        }
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
        try (FileWriter fileWriter = new FileWriter(ORDER_DATA_FILE, true); PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(order.toFileString());
            System.out.println("Order saved successfully.");
        }
    }

//        public void saveOrderToFile (Order order) throws IOException {
//            String ORDER_DATA_FILE = "orders.dat"; // Use .dat for binary data
//            try (FileOutputStream fileOut = new FileOutputStream(ORDER_DATA_FILE, true);
//                 ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
//                objectOut.writeObject(order);
//                System.out.println("Order saved successfully.");
//            }
//        }


//    private void saveOrderToFile(Order order) throws IOException {
//        String ORDER_DATA_FILE = "orders.txt";
//        File file = new File(ORDER_DATA_FILE);
//        boolean newFile = file.createNewFile(); // Create file if it doesn't exist
//        try (FileWriter fileWriter = new FileWriter(file, true);
//             PrintWriter printWriter = new PrintWriter(fileWriter)) {
//            if (newFile) {
//                printWriter.println("Order Details"); // Add header if new file
//            }
//            printWriter.printf("%s%n", order.toString());
//        }
}


