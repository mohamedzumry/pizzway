package com.example.pizzway.patterns.chainOfResponsibility;

import com.example.pizzway.models.Pizza;

public abstract class CustomizationHandler {
    protected CustomizationHandler nextHandler;

    public void setNextHandler(CustomizationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(CustomizationRequest request, Pizza pizza);
}

