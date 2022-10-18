package com.example.pizzadatabse.Menu.MenuItems;

import com.example.pizzadatabse.HelloApplication;
import com.example.pizzadatabse.Menu.MenuItems.Item;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private ArrayList<Item> dishes = new ArrayList<>();
    private String customerID;
    private String address;
    private String postcode;
    private int counter = 1;

    public Cart(String userName) {
        this.customerID = userName;

    }

    public void addDish(Item item) {


        this.dishes.add(item);
    }

    public void placeOrder() throws Exception {

        Connection conn = HelloApplication.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO orders(`id`, `username`,`address`, `postcode`, `date`, `time`, `amountofpizza`) VALUES (?,?,?,?,?,?,?)");
        LocalTime time = java.time.LocalTime.now();
        LocalDate date = java.time.LocalDate.now();
        PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM `customer` WHERE 'username'=\""+customerID+"\"");
        ResultSet set = stmt2.executeQuery();
        int ammount = 0;
        if(set.next()){
            ammount = set.getInt(6);
        }
        int counter = 0;
        for (Item dish : dishes) {
            if (dish.isPizza()) {
                counter++;
            }
        }
        if(ammount/10<(ammount+counter)/10){
            PreparedStatement stmt4 = conn.prepareStatement("INSERT INTO discounts(`id`) VALUES (?)");
            String discount = generateCode();
            stmt4.setString(1, discount);
            Stage stage = new Stage();
            GridPane pane = new GridPane();
            Label newCode = new Label("Your new discount code is: ");
            Label code = new Label(discount);
            pane.add(newCode,0,0);
            stmt4.execute();
            pane.add(code,1,0);
            stage.setScene(new Scene(pane, 200, 200));
            stage.show();
        }
        System.out.println("UPDATE `customer` SET `totalordered`="+(ammount+counter)+" WHERE `username`=\""+customerID+"\"");

        PreparedStatement stmt3 = conn.prepareStatement("UPDATE `customer` SET `totalordered`="+(ammount+counter)+" WHERE `username`=\""+customerID+"\"");
        stmt3.execute();

        stmt.setString(1, String.valueOf(counter));
        stmt.setString(2, customerID);
        stmt.setString(3, address);
        stmt.setString(4, postcode);
        stmt.setString(5, date.toString());
        stmt.setString(6, time.toString());
        int counters = 0;
        for (int i = 0; i < dishes.size(); i++) {
            if (dishes.get(i).isPizza()) {
                counters++;
            }
        }
        stmt.setString(7, String.valueOf(counters));
        stmt.executeUpdate();

    }

    public double calculatePrice() {
        double price = 0;
        for (int i = 0; i < dishes.size(); i++) {
            price+=dishes.get(i).getPrice();
        }
        return price;
    }

    public ArrayList<Item> getOrder() {
        return dishes;
    }
    public String generateCode(){
        String code = "";
        for (int i = 0; i < 10; i++) {
            code+=(ThreadLocalRandom.current().nextInt(0, 8));
        }
        return code;
    }

    public String getUsername() {
        return this.customerID;
    }
}