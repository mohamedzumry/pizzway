package com.example.pizzway.models;

import com.example.pizzway.patterns.observer.OrderObserver;

import java.util.ArrayList;
import java.util.List;

class OrderTracker {
    private List<OrderObserver> observers = new ArrayList<>();
    private String status;

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    private void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(status);
        }
    }
}
