package com.example.pizzway.patterns.decorator;


public abstract class PizzaDecorator implements PizzaComponent {
    protected PizzaComponent pizza;

    public PizzaDecorator(PizzaComponent pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double getPrice() {
        return pizza.getPrice();
    }
}

