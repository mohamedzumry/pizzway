package com.example.pizzway.models;

import com.example.pizzway.patterns.builder.PizzaBuilder;
import com.example.pizzway.patterns.decorator.PizzaComponent;

import java.util.ArrayList;
import java.util.List;

public class Pizza implements PizzaComponent {
    private String crust;
    private String cheese;
    private String sauce;
    private List<String> toppings;
    private double price;

    public Pizza(PizzaBuilder builder) {
        this.crust = builder.crust;
        this.cheese = builder.cheese;
        this.sauce = builder.sauce;
        this.toppings = builder.toppings;
        this.price = builder.price;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public String getCheese() {
        return cheese;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getDescription() {
        StringBuilder description = new StringBuilder("Pizza with ");
        description.append(crust).append(" crust, ");
        description.append(cheese).append(" cheese, ");
        description.append(sauce).append(" sauce");

        if (!toppings.isEmpty()) {
            description.append(", toppings: ").append(String.join(", ", toppings));
        }
        return description.toString();
    }

    @Override
    public double getPrice() {
        return price;
    }

}