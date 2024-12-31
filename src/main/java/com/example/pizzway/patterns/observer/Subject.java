package com.example.pizzway.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public interface Subject {
    void registerObserver(Observer observer);
    void unregisterObserver(Observer observer);
    void notifyObservers(String status);
}

