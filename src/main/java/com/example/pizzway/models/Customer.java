package com.example.pizzway.models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private String address;
    private int loyaltyPoints;
    private List<String> favorites;

    // Constructor
    public Customer(String userId, String name, String email, String phone, String address) {
        super(userId, name, email, phone);
        this.address = address;
        this.loyaltyPoints = 0;
        this.favorites = new ArrayList<>();
    }

    public Customer() {
        super();
    }

    // Getters and Setters
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public List<String> getFavorites() {
        return favorites;
    }

    // Methods
    public void saveFavoritePizza(String pizzaName) {
        favorites.add(pizzaName);
        System.out.println("Saved favorite pizza: " + pizzaName);
    }

    public void reorderFavoritePizza(String pizzaName) {
        if (favorites.contains(pizzaName)) {
            System.out.println("Reordered favorite pizza: " + pizzaName);
        } else {
            System.out.println("Pizza not found in favorites.");
        }
    }

    public void provideFeedback(String feedback) {
        System.out.println("Feedback received: " + feedback);
    }
}

