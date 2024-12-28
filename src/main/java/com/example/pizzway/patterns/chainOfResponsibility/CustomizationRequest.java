package com.example.pizzway.patterns.chainOfResponsibility;


public class CustomizationRequest {
    private String type; // The type of customization (e.g., "Crust", "Cheese", "Topping")
    private String detail; // The specific customization detail
    private double priceAdjustment; // Price adjustment for the customization

    public CustomizationRequest(String type, String detail, double priceAdjustment) {
        this.type = type;
        this.detail = detail;
        this.priceAdjustment = priceAdjustment;
    }

    public String getType() {
        return type;
    }

    public String getDetail() {
        return detail;
    }

    public double getPriceAdjustment() {
        return priceAdjustment;
    }
}
