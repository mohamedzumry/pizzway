package com.example.pizzway.models;

public class User {
    private String userId;
    private String name;
    private String email;
    private String phone;

    // Constructor
    public User(String userId, String name, String email, String phone) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public User() {

    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Common Methods
    public void login() {
        System.out.println(name + " logged in.");
    }

    public void updateProfile() {
        System.out.println("Profile updated for " + name);
    }
}

