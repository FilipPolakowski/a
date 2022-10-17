package com.example.pizzadatabse.Menu.MenuItems;

public abstract class Item {
    private String name;
    private double price;
    private String ID;

    public Item(String name, double price, String ID){
        this.name = name;
        this.price = price;
        this.ID = ID;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    public String getID() {
        return this.ID;
    }
}
