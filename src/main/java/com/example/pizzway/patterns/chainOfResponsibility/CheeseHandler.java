package com.example.pizzway.patterns.chainOfResponsibility;

import com.example.pizzway.models.Pizza;

public class CheeseHandler extends CustomizationHandler {
    @Override
    public void handleRequest(CustomizationRequest request, Pizza pizza) {
        if ("Cheese".equalsIgnoreCase(request.getType())) {
            pizza.setCheese(request.getDetail());
            pizza.setPrice(pizza.getPrice() + request.getPriceAdjustment());
            System.out.println("Added Cheese: " + request.getDetail());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request, pizza);
        }
    }
}
