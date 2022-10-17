package com.example.pizzadatabse.Menu.MenuItems;

import com.example.pizzadatabse.HelloApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Cart{
    private ArrayList<Item> dishes = new ArrayList<>();
    private String customerID;
    private String address;
    private String postcode;
    private int counter = 1;

    public void addDish(Item item){
        this.dishes.add(item);
    }

    private void placeOrder() throws Exception{

        Connection conn = HelloApplication.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO orders(`id`, `username`,`address`, `postcode`, `date`, `time`) VALUES (?,?,?,?,?,?)");
        LocalTime time = java.time.LocalTime.now();
        LocalDate date = java.time.LocalDate.now();

        stmt.setString(1, String.valueOf(counter));
        stmt.setString(2, customerID);
        stmt.setString(3, address);
        stmt.setString(4, postcode);
        stmt.setString(5, date.toString());
        stmt.setString(6, time.toString());
        counter++;

    }
    public ArrayList<Item> getOrder(){
        return dishes;
    }
    private double calculatePrice(){
        double price = 0;
        for (int i = 0; i < dishes.size(); i++) {
            price+=dishes.get(i).getPrice();
        }
        return price;
    }
}
