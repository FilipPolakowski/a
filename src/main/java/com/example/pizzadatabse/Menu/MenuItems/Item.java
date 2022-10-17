package com.example.pizzadatabse.Menu.MenuItems;

public abstract class Item {
    private String name;
    private double price;
    private String ID;
    boolean isPizza = false;

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
    public boolean isPizza(){
        return this.isPizza;
    }

    public String getID() {
        return this.ID;
    }
}
