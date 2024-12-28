package com.example.pizzway.patterns.state;

public class OutForDeliveryState implements OrderState {
    @Override
    public void next(OrderContext context) {
        context.setState(new DeliveredState());
    }

    @Override
    public void previous(OrderContext context) {
        context.setState(new PreparingState());
    }

    @Override
    public void printStatus() {
        System.out.println("Order is out for delivery.");
    }
}
