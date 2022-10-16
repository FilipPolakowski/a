package com.example.pizzadatabse.Menu.MenuItems;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Cart{
    private ArrayList<Item> dishes = new ArrayList<>();
    private String customerID;
    private String address;

    public void addDish(Item item){
        this.dishes.add(item);
    }
    private void placeOrderr(){
        //TODO send the order to database
        LocalTime time = java.time.LocalTime.now();
        LocalDate date = java.time.LocalDate.now();

    }
    private double calculatePrice(){
        double price = 0;
        for (int i = 0; i < dishes.size(); i++) {
            price+=dishes.get(i).getPrice();
        }
        return price;
    }
}
