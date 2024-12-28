package com.example.pizzway.patterns.state;

public class DeliveredState implements OrderState {
    @Override
    public void next(OrderContext context) {
        System.out.println("The order is already delivered.");
    }

    @Override
    public void previous(OrderContext context) {
        context.setState(new OutForDeliveryState());
    }

    @Override
    public void printStatus() {
        System.out.println("Order delivered.");
    }
}
