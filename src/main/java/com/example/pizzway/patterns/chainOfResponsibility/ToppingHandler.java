package com.example.pizzway.patterns.chainOfResponsibility;

import com.example.pizzway.models.Pizza;

public class ToppingHandler extends CustomizationHandler {
    @Override
    public void handleRequest(CustomizationRequest request, Pizza pizza) {
        if ("Topping".equalsIgnoreCase(request.getType())) {
            pizza.getToppings().add(request.getDetail());
            pizza.setPrice(pizza.getPrice() + request.getPriceAdjustment());
            System.out.println("Added Topping: " + request.getDetail());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request, pizza);
        }
    }
}

