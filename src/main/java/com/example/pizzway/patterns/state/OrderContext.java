package com.example.pizzway.patterns.state;

public class OrderContext {
    private OrderState state;

    public OrderContext() {
        state = new OrderPlacedState();
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void nextState() {
        state.next(this);
    }

    public void previousState() {
        state.previous(this);
    }

    public void printStatus() {
        state.printStatus();
    }
}