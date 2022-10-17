package com.example.pizzadatabse.Menu.MenuItems;

import com.example.pizzadatabse.HelloApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Cart{

    private ArrayList<Item> dishes = new ArrayList<>();
    private String customerID;
    private String address;
    private String postcode;
    private int delivery_status;
    private int counter;
    private LocalTime time;
    private LocalDate date;

    public Cart(){
        this.customerID = " ";
        this.address = " ";
        this.postcode = " ";
        this.delivery_status = 0;
        this.counter = 1;
        this.time = null;
        this.date = null;
    }

    public void addDish(Item item){
        this.dishes.add(item);
    }

    private void placeOrder() throws Exception{

        Cart cart = new Cart();

        Connection conn = HelloApplication.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO orders(`id`, `username`,`address`, `postcode`, `date`, `time`, `delivery_status`) VALUES (?,?,?,?,?,?,?)");
        cart.time = java.time.LocalTime.now();
        cart.date = java.time.LocalDate.now();

        stmt.setString(1, String.valueOf(counter));
        stmt.setString(2, customerID);
        stmt.setString(3, address);
        stmt.setString(4, postcode);
        stmt.setString(5, date.toString());
        stmt.setString(6, time.toString());
        stmt.setString(7, String.valueOf(delivery_status));
        counter++;

        stmt.executeUpdate();

        cart.DeliveryTimer();

    }

    public void DeliveryTimer(){
        LocalTime delivery = this.time.plusMinutes(30);
        Duration duration = Duration.between(this.time, delivery);
        Thread timer = new Thread(()->{
            try{
                while(this.time.getNano() < delivery.getNano()){
                    Thread.sleep(duration.toMillis());
                }
                delivery_status = 1;

                Connection conn = HelloApplication.getConnection();
                String id = this.customerID;
                PreparedStatement stmt = conn.prepareStatement("UPDATE orders SET delivery_status = 1 WHERE id in (" + id + ")" );
                stmt.setString(1, String.valueOf(delivery_status));

                stmt.executeUpdate();

            }catch(InterruptedException e){
                System.out.println(e);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        timer.start();
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
