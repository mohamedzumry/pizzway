package com.example.pizzway.patterns.decorator;


public class ExtraCheeseDecorator extends PizzaDecorator {
    private double extraCheeseCost;

    public ExtraCheeseDecorator(PizzaComponent pizza, double packagingCost) {
        super(pizza);
        this.extraCheeseCost = packagingCost;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Extra Cheese";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + extraCheeseCost;
    }
}
