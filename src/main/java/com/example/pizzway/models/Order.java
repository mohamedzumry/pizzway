package com.example.pizzway.models;


import com.example.pizzway.patterns.decorator.PizzaComponent;
import com.example.pizzway.patterns.observer.Observer;
import com.example.pizzway.patterns.observer.Subject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order implements Subject {
    private int id;
    private PizzaComponent pizza;
    private String customerUserName;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private LocalDateTime orderTime;
    private double totalPrice;
    private String orderType;
    private String paymentType;
    private String orderStatus;
    private List<Observer> observers = new ArrayList<>();

    public Order(int id, PizzaComponent pizza, String customerUserName, String customerName, String customerPhone, String customerAddress, double totalPrice, String orderType, String paymentType, String orderStatus) {
        this.id = id;
        this.pizza = pizza;
        this.customerUserName = customerUserName;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.orderTime = LocalDateTime.now(); // Automatically sets the order time
        this.totalPrice = totalPrice;
        this.orderType = orderType;
        this.paymentType = paymentType;
        this.orderStatus = orderStatus;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PizzaComponent getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public String getCustomerUserName() {
        return customerUserName;
    }

    public void setCustomerUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setPizza(PizzaComponent pizza) {
        this.pizza = pizza;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String status) {
        for (Observer observer : observers) {
            observer.update(status);
        }
    }

    // Convert the Order object to a string format for saving
    public String toFileString() {
        return getId() + "," + getCustomerUserName() + "," + getOrderSummary() + "," + getCustomerName() + "," + getCustomerAddress() + "," + getCustomerPhone();
    }

    // Create an Order object from a string format
//    public static Order fromFileString(String fileString) {
//        String[] parts = fileString.split(",");
//        return new Order(parts[0], parts[1], parts[2]);
//    }

    // Method to get a detailed order summary
    public String getOrderSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Customer: ").append(customerName).append("\n");
        summary.append("Phone: ").append(customerPhone).append("\n");
        summary.append("Address").append(customerAddress).append("\n");
        summary.append("Order Time: ").append(orderTime).append("\n");
        summary.append("Delivery Type: ").append(orderType).append("\n");
        summary.append("Payment Type: ").append(paymentType).append("\n");
        summary.append("Pizza: ").append(pizza.getDescription()).append("\n");
        summary.append("Total Price: $").append(String.format("%.2f", totalPrice));
        return summary.toString();
    }
}

