package com.example.pizzway.patterns.state;

public interface OrderState {
    void next(OrderContext context);
    void previous(OrderContext context);
    void printStatus();
}