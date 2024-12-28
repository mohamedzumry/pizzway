package com.example.pizzway.patterns.chainOfResponsibility;


import com.example.pizzway.models.Pizza;

public class CrustHandler extends CustomizationHandler {
    @Override
    public void handleRequest(CustomizationRequest request, Pizza pizza) {
        if ("Crust".equalsIgnoreCase(request.getType())) {
            pizza.setCrust(request.getDetail());
            pizza.setPrice(pizza.getPrice() + request.getPriceAdjustment());
            System.out.println("Added Crust: " + request.getDetail());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request, pizza);
        }
    }
}
