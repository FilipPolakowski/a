package com.example.pizzadatabse.Menu.MenuItems;

public class Topping extends Item {
    boolean isVegeterian;
    public Topping(String name, double price, boolean isVegeterian) {
        super(name, price);
        this.isVegeterian = isVegeterian;
    }
}
