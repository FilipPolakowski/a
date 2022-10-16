package com.example.pizzadatabse.Menu.MenuItems;

import java.util.ArrayList;

public class Pizza extends  Item{
    private String id;
    private boolean isVegeterian = true;
    private ArrayList<Topping> topping;
    public Pizza(String name, ArrayList<Topping> toppings, String id) {
        super(name, calculatePrice(toppings));
        this.id = id;
        for (int i = 0; i < toppings.size(); i++) {
            if(!toppings.get(i).isVegeterian){
                isVegeterian = false;
            }
        }
        this.topping = toppings;
    }

    private static double calculatePrice(ArrayList<Topping> toppings) {
        double price = 5;
        for (int i = 0; i < toppings.size(); i++) {
            price+=toppings.get(i).getPrice();
        }
        return price;
    }
    public String getID(){
        return id;
    }
    public String getIsVegetarian(){
        if(isVegeterian){
            return "Vegetarian";
        }
        return "Not vegetarian";
    }
    public String getToppings(){
        String toppings = "";
        for (int i = 0; i < topping.size(); i++) {
            toppings+=(topping.get(i).getName() +", ");
        }
        toppings = toppings.substring(0, toppings.length()-2);
        return toppings;
    }

}
