package com.example.pizzadatabse.Menu;


import com.example.pizzadatabse.Menu.MenuItems.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class Menu {
    private static Cart cart = new Cart();

    public static void displayMenu(Stage menuStage) {
        GridPane menuGroup = new GridPane();
        menuGroup.setAlignment(Pos.CENTER);
        menuGroup.setHgap(15);
        menuGroup.setVgap(15);
        menuGroup.setPadding(new Insets(25, 25, 25, 25));


        menuStage.setTitle("Menu");


        menuStage.setScene(new Scene(menuGroup, 500, 500));
        EventHandler<ActionEvent> pizzas = new EventHandler<>() {
            public void handle(ActionEvent e) {
                Stage pizzas = new Stage();
                GridPane pizzaGroup = new GridPane();
                pizzaGroup.setHgap(20);
                pizzaGroup.setVgap(20);
                pizzaGroup.setAlignment(Pos.BASELINE_LEFT);
                ArrayList<Pizza> listOfPizzas = getData("pizza", 3);


                //TODO get all pizzas from database and add them to arrayList

                for (int i = 0; i < listOfPizzas.size(); i++) {
                    final int a = i;
                    Label ID = new Label(listOfPizzas.get(i).getID());
                    ID.setFont(new Font("Arial", 14));
                    Label Name = new Label(listOfPizzas.get(i).getName());
                    Name.setFont(new Font("Arial", 14));
                    Label Toppings = new Label(listOfPizzas.get(i).getToppings());
                    Toppings.setFont(new Font("Arial", 14));
                    Label Price = new Label(String.valueOf(listOfPizzas.get(i).getPrice() * 1.4 * 1.09));
                    Price.setFont(new Font("Arial", 14));
                    Label Vegetarian = new Label(listOfPizzas.get(i).getIsVegetarian());
                    Vegetarian.setFont(new Font("Arial", 14));
                    pizzaGroup.add(ID, 1, i);

                    pizzaGroup.add(Name, 2, i);
                    pizzaGroup.add(Toppings, 3, i);

                    pizzaGroup.add(Price, 4, i);

                    pizzaGroup.add(Vegetarian, 5, i);
                    EventHandler<ActionEvent> orderPizza = new EventHandler<>() {
                        public void handle(ActionEvent e) {
                            int id = a;
                            cart.addDish(listOfPizzas.get(a));
                        }
                    };
                    Button order = new Button("add to cart");
                    order.setOnAction(orderPizza);
                    pizzaGroup.add(order, 6, i);


                }
                Scene pizzaScene = new Scene(pizzaGroup, 1000, 500);
                pizzas.setScene(pizzaScene);
                pizzas.show();

            }
        };

        EventHandler<ActionEvent> desertsAndDrinks = new EventHandler<>() {
            public void handle(ActionEvent e) {
                Stage drinksAndDesertss = new Stage();
                GridPane drAndDesGroup = new GridPane();
                drAndDesGroup.setHgap(20);
                drAndDesGroup.setVgap(20);
                drAndDesGroup.setAlignment(Pos.BASELINE_LEFT);
                ArrayList<Item> listOfDrinksAndDesserts = new ArrayList<>();


                listOfDrinksAndDesserts.add(new DesertsAndDrinks("Tiramisu", 3, "1"));
                listOfDrinksAndDesserts.add(new DesertsAndDrinks("Ice Crea,", 4, "1"));
                listOfDrinksAndDesserts.add(new DesertsAndDrinks("Cola", 2, "2"));
                listOfDrinksAndDesserts.add(new DesertsAndDrinks("Fanta", 1, "3"));


                //TODO get all pizzas from database and add them to arrayList

                for (int i = 0; i < listOfDrinksAndDesserts.size(); i++) {
                    final int a = i;
                    Label ID = new Label(listOfDrinksAndDesserts.get(i).getID());
                    ID.setFont(new Font("Arial", 14));
                    Label Name = new Label(listOfDrinksAndDesserts.get(i).getName());
                    Name.setFont(new Font("Arial", 14));

                    Label Price = new Label(String.valueOf(listOfDrinksAndDesserts.get(i).getPrice() * 1.4 * 1.09));
                    Price.setFont(new Font("Arial", 14));

                    drAndDesGroup.add(ID, 1, i);

                    drAndDesGroup.add(Name, 2, i);

                    drAndDesGroup.add(Price, 3, i);

                    EventHandler<ActionEvent> orderPizza = new EventHandler<>() {
                        public void handle(ActionEvent e) {
                            int id = a;
                            cart.addDish(listOfDrinksAndDesserts.get(a));
                        }
                    };
                    Button order = new Button("add to cart");
                    order.setOnAction(orderPizza);
                    drAndDesGroup.add(order, 4, i);


                }
                Scene pizzaScene = new Scene(drAndDesGroup, 1000, 500);
                drinksAndDesertss.setScene(pizzaScene);
                drinksAndDesertss.show();

            }
        };
        EventHandler<ActionEvent> cartevent = new EventHandler<>() {
            public void handle(ActionEvent e) {
            }
        };


        Button pizza = new Button("Pizzas");
        menuGroup.add(pizza, 0, 0);
        pizza.setOnAction(pizzas);

        Button deserts = new Button("Deserts");
        menuGroup.add(deserts, 1, 0);
        deserts.setOnAction(desertsAndDrinks);

        Button drinks = new Button("Drinks");
        menuGroup.add(drinks, 1, 1);
        pizza.setOnAction(pizzas);

        Button cart = new Button("Shopping cart");
        menuGroup.add(cart, 0, 1);
        cart.setOnAction(cartevent);
    }

    public static ArrayList<Pizza> getData(String whichData, int howManyColumns) {
        ArrayList<Pizza> pizzas = new ArrayList<>();

        try {
            String url = "jdbc:mysql://localhost:3306/pizzaapi";
            String login = "abc";
            String passwords = "password";
            String query = "SELECT * FROM `" + whichData + "`";

            Connection conn = DriverManager.getConnection(url, login, passwords);
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                pizzas.add(new Pizza(results.getString(1), getDataToppings(results.getString(2)), results.getString(3)));
                System.out.print(results.getInt(1));
                System.out.print(": ");
                System.out.println(results.getString(2));
                System.out.println(results.getString(3));


            }


        } catch (Exception a) {
            throw new IllegalStateException("Cannot find database", a);
        }
        return pizzas;
    }

    public static ArrayList<Topping> getDataToppings(String whichToppings) {
        ArrayList<Topping> toppings = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < whichToppings.length() / 2; i++) {
            strings.add(whichToppings.substring(i, i + 1));
            try {
                String url = "jdbc:mysql://localhost:3306/pizzaapi";
                String login = "abc";
                String passwords = "password";
                String query = "SELECT " + strings.remove(0) + " FROM  `toppings`";
                Connection conn = DriverManager.getConnection(url, login, passwords);
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet results = stmt.executeQuery();
                toppings.add(new Topping(results.getString(2), results.getDouble(3), results.getInt(4) == 1, results.getString(1)));
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        return toppings;

    }
}
