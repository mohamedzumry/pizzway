package com.example.pizzway.patterns.state;

public class OrderPlacedState implements OrderState {
    @Override
    public void next(OrderContext context) {
        context.setState(new PreparingState());
    }

    @Override
    public void previous(OrderContext context) {
        System.out.println("The order is in its initial state.");
    }

    @Override
    public void printStatus() {
        System.out.println("Order placed.");
    }
}
