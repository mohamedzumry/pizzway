package com.example.pizzway.patterns.builder;

import com.example.pizzway.models.Pizza;

import java.util.ArrayList;
import java.util.List;

    public  class PizzaBuilder {
        public String crust;
        public String cheese;
        public String sauce;
        public List<String> toppings = new ArrayList<>();
        public double price = 0.0;

        public PizzaBuilder setCrust(String crust, double price) {
            this.crust = crust;
            this.price += price;
            return this;
        }

        public PizzaBuilder setCheese(String cheese, double price) {
            this.cheese = cheese;
            this.price += price;
            return this;
        }

        public PizzaBuilder setSauce(String sauce, double price) {
            this.sauce = sauce;
            this.price += price;
            return this;
        }

        public PizzaBuilder addTopping(String topping, double price) {
            this.toppings.add(topping);
            this.price += price;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }
