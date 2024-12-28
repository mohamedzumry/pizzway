package com.example.pizzway.patterns.chainOfResponsibility;

import com.example.pizzway.models.Pizza;

public class SauceHandler extends CustomizationHandler {
    @Override
    public void handleRequest(CustomizationRequest request, Pizza pizza) {
        if ("Sauce".equalsIgnoreCase(request.getType())) {
            pizza.setSauce(request.getDetail());
            pizza.setPrice(pizza.getPrice() + request.getPriceAdjustment());
            System.out.println("Added Sauce: " + request.getDetail());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request, pizza);
        }
    }
}
