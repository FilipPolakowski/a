package com.example.pizzadatabse.Menu.MenuItems;

public class Topping extends Item {
    boolean isVegeterian;
    public Topping(String name, double price, boolean isVegeterian, String ID) {
        super(name, price, ID);
        this.isVegeterian = isVegeterian;
    }
}
