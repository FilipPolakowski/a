package com.example.pizzadatabse.Menu.MenuItems;

import java.util.ArrayList;

public class Pizza extends  Item{
    private boolean isVegeterian = true;
    private ArrayList<Topping> topping;
    public Pizza(String name, ArrayList<Topping> toppings, String id) {
        super(name, calculatePrice(toppings), id);
        for (int i = 0; i < toppings.size(); i++) {
            if(!toppings.get(i).isVegeterian){
                isVegeterian = false;
            }
        }
        this.topping = toppings;
        this.isPizza = true;
    }

    private static double calculatePrice(ArrayList<Topping> toppings) {
        double price = 5;
        for (int i = 0; i < toppings.size(); i++) {
            price+=toppings.get(i).getPrice();
        }
        return price;
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

        return toppings;
    }

}
