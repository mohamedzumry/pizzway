package com.example.pizzway.patterns.decorator;


public class SpecialPackagingDecorator extends PizzaDecorator {
    private double packagingCost;

    public SpecialPackagingDecorator(PizzaComponent pizza, double packagingCost) {
        super(pizza);
        this.packagingCost = packagingCost;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Special Packaging";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + packagingCost;
    }
}

