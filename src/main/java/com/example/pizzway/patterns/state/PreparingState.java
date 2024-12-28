package com.example.pizzway.patterns.state;

public class PreparingState implements OrderState {
    @Override
    public void next(OrderContext context) {
        context.setState(new OutForDeliveryState());
    }

    @Override
    public void previous(OrderContext context) {
        context.setState(new OrderPlacedState());
    }

    @Override
    public void printStatus() {
        System.out.println("Order is being prepared.");
    }
}
