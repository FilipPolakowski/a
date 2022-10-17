package com.example.pizzadatabse;

import com.example.pizzadatabse.Menu.MenuItems.Cart;
import com.example.pizzadatabse.Menu.MenuItems.Item;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Order {
    private String id;
    private String loginOfCustomer;
    ArrayList<Item> dishes = new java.util.ArrayList<>();
    private String customerID;
    private String address;
    private LocalTime time;
    private LocalDate date;
    private double price;

    public Order(String id, String loginOfCustomer, ArrayList<Item> dishes, String customerID, String address, LocalTime time, LocalDate date){
         this.id = id;
         this.loginOfCustomer = loginOfCustomer;
         this.dishes = dishes;
         this.customerID = customerID;
         this.address = address;
         this.time = time;
         this.date = date;
    }

    public String getID() {
        return id;
    }

    public LocalTime whenOrdered() {
        return time;
    }

    public double getPrice() {
        return price;
    }
}
