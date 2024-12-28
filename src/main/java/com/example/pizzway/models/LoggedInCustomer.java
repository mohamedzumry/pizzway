package com.example.pizzway.models;

public class LoggedInCustomer extends Customer {
    private static LoggedInCustomer instance;

    private String username;
    private String address;
    private String phone;

    // Private constructor to prevent instantiation
    private LoggedInCustomer() {
        super();
    }

    // Get the singleton instance
    public static LoggedInCustomer getInstance() {
        if (instance == null) {
            instance = new LoggedInCustomer();
        }
        return instance;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

