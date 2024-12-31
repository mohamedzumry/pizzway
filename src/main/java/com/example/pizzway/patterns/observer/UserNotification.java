package com.example.pizzway.patterns.observer;

public class UserNotification implements Observer {
    private String username;

    public UserNotification(String username) {
        this.username = username;
    }

    @Override
    public void update(String status) {
        System.out.println("Notification to " + username + ": Your order status is now '" + status + "'.");
    }
}

